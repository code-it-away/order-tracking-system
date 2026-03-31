# 🛒 Order Tracking System

## 📌 Overview

A production-grade backend system to create and track orders with status transitions.
This project is designed with **scalability, consistency, and real-world system design principles** in mind.

---

## 🚀 Features

* Create orders
* Track order by ID
* Update order status (PLACED → SHIPPED → DELIVERED → CANCELLED)
* Maintain complete order history (timeline)
* Clean layered architecture (Controller → Service → Repository)

---

## 🏗️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL / MySQL**
* **Maven**

---

## 🧠 System Design Goals

* Handle moderate scale (~50 orders/sec)
* Ensure strong consistency for order state
* Support audit trail via order history
* Design for future scalability (Kafka, Redis, idempotency)

---

## 🏛️ Architecture

```
Client → Controller → Service → Repository → Database
```

* **Controller** → Handles REST APIs
* **Service** → Business logic & validations
* **Repository** → Database interaction
* **Database** → Persistent storage

---

## 📊 Database Design

### Order Table

* `id` (Primary Key)
* `user_id`
* `status`
* `created_at`
* `updated_at`

### Order History Table

* `id` (Primary Key)
* `order_id` (Foreign Key)
* `status`
* `created_at`

> OrderHistory maintains a complete timeline of status changes for each order.

---

## 🔌 APIs

### Create Order

```
POST /orders
```

### Get Order

```
GET /orders/{id}
```

### Update Order Status

```
PATCH /orders/{id}/status
```

### Get Order History

```
GET /orders/{id}/history
```

---

## ⚙️ Key Design Decisions

### 1. SQL Database

Chosen for strong consistency and structured relational data.

### 2. Separate Order History Table

* Avoids bloating Order table
* Enables efficient tracking queries
* Maintains audit trail

### 3. Enum Stored as STRING

Prevents data corruption due to enum reordering.

### 4. Lean Entity Design

Order entity does not contain history list to avoid unnecessary data fetching.

---

## 🔮 Future Enhancements

* Idempotent order creation
* Async processing (Kafka / RabbitMQ)
* Caching (Redis)
* Rate limiting
* Retry & failure handling
* Distributed ID generation (Snowflake/UUID)

---

## 📈 Learning Focus

This project is built to:

* Strengthen backend system design skills
* Prepare for product-based company interviews
* Demonstrate real-world engineering thinking

---

## 🤝 Contribution

This is a personal learning project, but suggestions and improvements are welcome.

---
