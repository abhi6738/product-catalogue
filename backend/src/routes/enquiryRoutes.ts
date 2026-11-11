
import { Router } from "express";

import {
    EnquiryService,
    Enquiry
} from "../services/enquiryService.js";

export function createEnquiryRoutes(
    service: EnquiryService
): Router {

    const router = Router();

    // POST /api/enquiries
    router.post("/", async (req, res, next) => {

        try {
            const {
                productId,
                customerName,
                email,
                message
            } = req.body;

            // Validate customer details
            if (
                !Number.isInteger(productId) ||
                productId <= 0 ||
                typeof customerName !== "string" ||
                !customerName.trim() ||
                typeof email !== "string" ||
                !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) ||
                typeof message !== "string" ||
                !message.trim()
            ) {
                return res.status(400).json({
                    message: "Invalid enquiry details"
                });
            }

            const enquiry: Enquiry = {
                productId,
                customerName: customerName.trim(),
                email: email.trim(),
                message: message.trim()
            };

            await service.submit(enquiry);

            return res.status(201).json({
                success: true,
                message: "Thank you! Your enquiry has been recorded."
            });

        } catch (error) {
            next(error);
        }
    });

    return router;
}
