# Distributed-Inventory-Order-Processing-system
📌 Overview

This project is a multi-tenant inventory and order processing backend designed to simulate real-world B2B commerce workflows. It supports secure, isolated operations for multiple tenants, ensuring that each tenant’s data and transactions remain independent.

The system provides transactional APIs for product management, order processing, and inventory updates, with built-in validation, fault tolerance, and lifecycle management.

🏗️ Architecture

Client (curl / API client)
↓
Spring Boot REST API
↓
JWT Authentication + RBAC
↓
Tenant Context (request-scoped)
↓
Service Layer (Order, Inventory, Product)
↓
PostgreSQL (via JPA + Flyway)

Key Components
Spring Boot (Java 21) — REST API layer
PostgreSQL — relational database
JWT Authentication — stateless security
Tenant Context — request-level tenant isolation
Flyway — database schema versioning
Docker + Docker Compose — containerized deployment
AWS EC2 — cloud deployment environment

🔐 Key Features

✅ Multi-Tenant Architecture
Each request is scoped to a tenant using JWT claims
Data isolation enforced at query level (tenant_id filtering)

✅ Authentication & Authorization
JWT-based stateless authentication
Role-Based Access Control (RBAC)

✅ Order Processing Workflow
Create orders with multiple items
Inventory validation before order placement
Transactional consistency

✅ Order Lifecycle Management
CREATED → CONFIRMED → CANCELLED
Protected state transitions (invalid transitions blocked)

✅ Inventory Management
Stock updates during order creation
Prevents overselling using validation + locking

✅ Fault Tolerance
Retry mechanism for transient failures
Graceful handling of invalid operations
Centralized exception handling

✅ Database Management
Flyway-based schema migrations
Handles real-world migration scenarios (identity column fixes)

📦 API Endpoints
🔑 Authentication
curl -X POST http://<HOST>:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
"username": "admin",
"password": "Admin@123"
}'

📦 Products
curl -X GET http://<HOST>:8080/products \
-H "Authorization: Bearer <TOKEN>"

🛒 Create Order
curl -X POST http://<HOST>:8080/orders \
-H "Authorization: Bearer <TOKEN>" \
-H "Content-Type: application/json" \
-d '{
"items": [
{
"productId": 1,
"quantity": 1
}
]
}'

✅ Confirm Order
curl -X PATCH http://<HOST>:8080/orders/{id}/confirm \
-H "Authorization: Bearer <TOKEN>"

❌ Cancel Order
curl -X PATCH http://<HOST>:8080/orders/{id}/cancel \
-H "Authorization: Bearer <TOKEN>"

🗄️ Database Design
Tables
product
orders
order_items
users

Multi-Tenant Strategy

Each table includes: tenant_id BIGINT

All queries are filtered by tenant context.

🐳 Running Locally (Docker)
docker compose up --build

Services:
Backend → http://localhost:8080
PostgreSQL → localhost:5432

☁️ Deployment (AWS EC2)

The application is deployed using:

Dockerized backend
PostgreSQL container
Docker Compose orchestration

Steps
Launch Ubuntu EC2 instance
Install Docker
Clone repository
Run:
docker compose up --build -d
Access API
http://<EC2_PUBLIC_IP>:8080

🧪 Default Users (Seed Data)
Username	Password	Role	Tenant
admin	Admin@123	ADMIN	1
tenant2admin	Admin@123	ADMIN	2

⚙️ Tech Stack
Java 21
Spring Boot 3
Spring Security
PostgreSQL
Flyway
Docker
AWS EC2
Maven

📈 Key Engineering Highlights
Designed a multi-tenant backend system with strict data isolation
Implemented transactional order workflows with inventory consistency
Added fault tolerance using retry mechanisms and guarded state transitions
Managed database schema evolution using Flyway in a containerized environment
Deployed application on AWS EC2 using Docker Compose for reproducibility

🚀 Future Improvements
Split into microservices (Order, Inventory, Auth)
Add API Gateway
Introduce Kafka for event-driven workflows
Add monitoring (Prometheus + Grafana)
Load testing and performance benchmarking

📌 Author

Charitha Chinnapapannagari
Backend Engineer | AI Systems | Distributed Systems

🔥 Done.