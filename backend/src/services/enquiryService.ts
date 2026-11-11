
export interface Enquiry {
    productId: number;
    customerName: string;
    email: string;
    message: string;
}

// Contract for future email providers
export interface EmailProvider {
    sendEmail(
        recipient: string,
        subject: string,
        body: string
    ): Promise<void>;
}

// Local implementation for development and assessment
export class ConsoleEmailProvider implements EmailProvider {

    async sendEmail(
        recipient: string,
        subject: string,
        body: string
    ): Promise<void> {

        console.log("\n========== SALES ENQUIRY ==========");
        console.log("To:", recipient);
        console.log("Subject:", subject);
        console.log(body);
        console.log("===================================\n");
    }
}

// Business logic for customer enquiries
export class EnquiryService {

    constructor(
        private readonly emailProvider: EmailProvider,
        private readonly salesEmail: string
    ) {}

    async submit(enquiry: Enquiry): Promise<void> {

        const body = `
Customer: ${enquiry.customerName}
Email: ${enquiry.email}
Product ID: ${enquiry.productId}
Message: ${enquiry.message}
`;

        await this.emailProvider.sendEmail(
            this.salesEmail,
            `Product enquiry - ${enquiry.productId}`,
            body
        );
    }
}
