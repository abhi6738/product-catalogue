
import { Router } from "express";
import { ProductService } from "../services/productService.js";

export function createProductRoutes(
    service: ProductService
): Router {

    const router = Router();

    // GET /api/products
    // GET /api/products?search=shirt
    router.get("/", async (req, res, next) => {
        try {
            const query = String(req.query.search || "");

            if (query.trim()) {
                return res.json(await service.search(query));
            }

            return res.json(await service.getAll());

        } catch (error) {
            next(error);
        }
    });

    // GET /api/products/featured
    router.get("/featured", async (_req, res, next) => {
        try {
            res.json(await service.getFeatured());
        } catch (error) {
            next(error);
        }
    });

    // GET /api/products/categories
    router.get("/categories", async (_req, res, next) => {
        try {
            res.json(await service.getCategories());
        } catch (error) {
            next(error);
        }
    });

    // GET /api/products/category/electronics
    router.get("/category/:category", async (req, res, next) => {
        try {
            res.json(
                await service.getByCategory(req.params.category)
            );
        } catch (error) {
            next(error);
        }
    });

    // GET /api/products/1
    router.get("/:id", async (req, res, next) => {
        try {
            const id = Number(req.params.id);

            if (!Number.isInteger(id) || id <= 0) {
                return res.status(400).json({
                    message: "Invalid product ID"
                });
            }

            const product = await service.getById(id);

            if (!product) {
                return res.status(404).json({
                    message: "Product not found"
                });
            }

            return res.json(product);

        } catch (error) {
            next(error);
        }
    });

    return router;
}
