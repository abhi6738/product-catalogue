\# Product Catalogue



An Android product catalogue application backed by a Node.js and TypeScript REST API.



The application retrieves product information from Fake Store API through the backend. The Android client communicates only with our backend, keeping external API integration out of the client.



\## Tech Stack



\### Android

\- Kotlin

\- Jetpack Compose

\- MVVM architecture

\- StateFlow and Coroutines

\- Retrofit and Gson

\- Navigation Compose

\- Coil for image loading



\### Backend

\- Node.js

\- TypeScript

\- Express.js

\- Fake Store API

\- Vitest (backend testing)



\## Project Structure



```text

product-catalogue/

├── android/

│   └── app/

│       └── src/main/

│           ├── java/com/example/productcatalogue/

│           │   ├── data/

│           │   │   ├── model/

│           │   │   ├── remote/

│           │   │   └── repository/

│           │   ├── domain/

│           │   ├── presentation/

│           │   │   ├── home/

│           │   │   ├── category/

│           │   │   ├── search/

│           │   │   ├── detail/

│           │   │   └── enquiry/

│           │   └── navigation/

│           └── AndroidManifest.xml

├── backend/

│   ├── src/

│   │   ├── config/

│   │   ├── providers/

│   │   ├── routes/

│   │   ├── services/

│   │   └── index.ts

│   ├── .env.example

│   ├── package.json

│   └── tsconfig.json

├── .gitignore

├── README.md

├── DECISIONS.md

└── AI\_USAGE.md

```



\## Prerequisites



Install the following:



\- Node.js (LTS)

\- npm

\- Android Studio

\- Android Emulator or Android device



\## Running the Backend



Open a terminal in the repository root.



```bash

cd backend

npm install

```



Create a `.env` file inside the `backend` directory using `.env.example` as a reference.



Example:



```env

PORT=3000

FAKE\_STORE\_API=https://fakestoreapi.com

SALES\_EMAIL=sales@example.com

```



Start the backend:



```bash

npm run dev

```



The backend will be available at:



```text

http://localhost:3000

```



Check the health endpoint:



```text

http://localhost:3000/health

```



Keep the backend terminal running while using the Android application.



\## Running the Android Application



1\. Open Android Studio.

2\. Select \*\*Open\*\* and choose the repository's `android` directory.

3\. Allow Gradle synchronization to complete.

4\. Start an Android Emulator.

5\. Run the `app` configuration.



\### Emulator-to-Backend Connection



The Android Emulator uses a different network environment from the Windows host.



For local development, configure the Retrofit base URL as:



```kotlin

private const val BASE\_URL = "http://127.0.0.1:3000/"

```



Before running the app, execute this command in a terminal:



```bash

adb reverse tcp:3000 tcp:3000

```



This forwards the emulator's port 3000 to the backend running on the host machine.



If `adb` is not recognized, use the full path:



```powershell

\& "$env:LOCALAPPDATA\\Android\\Sdk\\platform-tools\\adb.exe" reverse tcp:3000 tcp:3000

```



The backend must be running before launching the app.



\*\*Note:\*\* The current HTTP configuration is intended for local development. Production deployments should use HTTPS.



\## Backend API Endpoints



Base URL:



```text

http://localhost:3000

```



| Method | Endpoint | Description |

|---|---|---|

| GET | `/health` | Backend health check |

| GET | `/api/products` | Retrieve all products |

| GET | `/api/products/featured` | Retrieve five top-rated products |

| GET | `/api/products/categories` | Retrieve available categories |

| GET | `/api/products/category/:category` | Retrieve products by category |

| GET | `/api/products?search=shirt` | Search products |

| GET | `/api/products/:id` | Retrieve product details |

| POST | `/api/enquiries` | Submit a product enquiry |



\### Enquiry Request Example



```json

{

&#x20; "productId": 1,

&#x20; "customerName": "John Doe",

&#x20; "email": "john@example.com",

&#x20; "message": "I would like to know more about this product."

}

```



\## Architecture



The Android application follows MVVM with a separation between presentation and data responsibilities.



```text

Compose UI

&#x20;   |

&#x20;   v

ViewModel

&#x20;   |

&#x20;   v

Repository

&#x20;   |

&#x20;   v

Retrofit API Service

&#x20;   |

&#x20;   v

Node.js Backend

&#x20;   |

&#x20;   v

Product Provider

&#x20;   |

&#x20;   v

Fake Store API

```



The backend uses provider and service abstractions to separate external API integration from business logic.



\## Email Configuration and Current Limitation



The enquiry endpoint currently uses a `ConsoleEmailProvider`.



Enquiry details are validated and passed to the email provider, which logs the recipient, subject, and message to the backend terminal.



\*\*Actual email delivery is not implemented yet.\*\* A production email provider or SMTP integration would be required to send enquiries to the sales inbox.



\## Design Decisions



See \[DECISIONS.md](DECISIONS.md) for architecture choices, trade-offs, and implementation limitations.



\## AI Usage



See \[AI\_USAGE.md](AI\_USAGE.md) for details about AI assistance used during development.



\## Current Implementation Status



The backend API and Android featured-products screen are implemented. Additional catalogue flows, enquiry UI, automated tests, and final integration are subject to completion before submission.



\## License



Created as part of an engineering assessment.



