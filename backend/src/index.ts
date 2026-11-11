
import express from "express";
import cors from "cors";

import { env } from "./config/env.js";

import {
    FakeStoreProductProvider
} from "./providers/productProvider.js";

import {
    ProductService
} from "./services/productService.js";

import {
    ConsoleEmailProvider,
    EnquiryService
} from "./services/enquiryService.js";

import {
    createProductRoutes
} from "./routes/productRoutes.js";

import {
    createEnquiryRoutes
} from "./routes/enquiryRoutes.js";

// Create Express application
const app = express();

// Middleware
app.use(cors());
app.use(express.json({ limit: "100kb" }));

// Initialize product dependencies
const productProvider = new FakeStoreProductProvider();

const productService = new ProductService(
    productProvider
);

// Initialize enquiry dependencies
const emailProvider = new ConsoleEmailProvider();

const enquiryService = new EnquiryService(
    emailProvider,
    env.salesEmail
);

// Health check API
app.get("/health", (_req, res) => {
    res.json({
        status: "UP",
        message: "Product Catalogue Backend is running"
    });
});

// Register product routes
app.use(
    "/api/products",
    createProductRoutes(productService)
);

// Register enquiry routes
app.use(
    "/api/enquiries",
    createEnquiryRoutes(enquiryService)
);

// Centralized error handling
app.use((
    error: unknown,
    _req: express.Request,
    res: express.Response,
    _next: express.NextFunction
) => {

    console.error("Backend error:", error);

    res.status(502).json({
        message: "Something went wrong. Please try again."
    });
});

// Start server
app.listen(env.port, "0.0.0.0", () => {
    console.log(
        `Backend running at http://localhost:${env.port}`
    );
});
