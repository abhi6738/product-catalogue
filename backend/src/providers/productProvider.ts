
import { env } from "../config/env.js";

// Product model received from Fake Store API
export interface Product {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;

    rating: {
        rate: number;
        count: number;
    };
}

// Contract for all future product data sources
export interface ProductProvider {
    getAllProducts(): Promise<Product[]>;

    getProductById(id: number): Promise<Product | null>;
}

// Current implementation using Fake Store API
export class FakeStoreProductProvider
    implements ProductProvider {

    async getAllProducts(): Promise<Product[]> {

        const response = await fetch(
            `${env.fakeStoreApi}/products`
        );

        if (!response.ok) {
            throw new Error("Unable to fetch products");
        }

        return await response.json() as Product[];
    }

    async getProductById(id: number): Promise<Product | null> {

        const response = await fetch(
            `${env.fakeStoreApi}/products/${id}`
        );

        if (response.status === 404) {
            return null;
        }

        if (!response.ok) {
            throw new Error("Unable to fetch product");
        }

        return await response.json() as Product;
    }
}   