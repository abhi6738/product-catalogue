
import dotenv from "dotenv";

// Load environment variables from .env
dotenv.config();

export const env = {
    port: Number(process.env.PORT) || 3000,

    fakeStoreApi:
        process.env.FAKE_STORE_API ||
        "https://fakestoreapi.com",

    salesEmail:
        process.env.SALES_EMAIL ||
        "sales@example.com"
};