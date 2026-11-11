
import {
    Product,
    ProductProvider
} from "../providers/productProvider.js";

export class ProductService {

    constructor(
        private readonly provider: ProductProvider
    ) {}

    // Get all products
    async getAll(): Promise<Product[]> {
        return this.provider.getAllProducts();
    }

    // Get five highest-rated products
    async getFeatured(): Promise<Product[]> {

        const products = await this.getAll();

        return [...products]
            .sort((a, b) =>
                b.rating.rate - a.rating.rate
            )
            .slice(0, 5);
    }

    // Get unique categories
    async getCategories(): Promise<string[]> {

        const products = await this.getAll();

        return [
            ...new Set(
                products.map(product => product.category)
            )
        ];
    }

    // Get products belonging to a category
    async getByCategory(
        category: string
    ): Promise<Product[]> {

        const products = await this.getAll();

        return products
            .filter(product =>
                product.category.toLowerCase() ===
                category.toLowerCase()
            )
            .sort((a, b) =>
                b.rating.rate - a.rating.rate
            );
    }

    // Search by title, description or category
    async search(query: string): Promise<Product[]> {

        const products = await this.getAll();

        const normalizedQuery = query.trim().toLowerCase();

        if (!normalizedQuery) {
            return [];
        }

        return products.filter(product =>
            product.title.toLowerCase().includes(normalizedQuery) ||
            product.description.toLowerCase().includes(normalizedQuery) ||
            product.category.toLowerCase().includes(normalizedQuery)
        );
    }

    // Get individual product details
    async getById(id: number): Promise<Product | null> {
        return this.provider.getProductById(id);
    }
}
