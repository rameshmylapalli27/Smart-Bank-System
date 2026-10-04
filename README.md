# 🏦 Smart Bank Management System (Pure Java SE & Swing)

A lightweight, desktop-based banking application built from scratch using **Java Standard Edition (SE)**, **Java Swing** for the graphical user interface, and **MySQL** for data persistence via **JDBC / JdbcRowSet**. 

This application simulates a complete ATM and core banking environment, enabling account creation, cash deposits/withdrawals, PIN modifications, balance checks, and secure session tracking without relying on heavy frameworks like Spring.

---

## 🚀 Key Features

* **Secure Authentication & PIN Management:** Secure login system backed by encrypted/verified database records with options to update security PINs on the fly.
* **Interactive ATM GUI:** Custom-designed graphical ATM interface with a background layout, responsive action buttons, and input validations using Regular Expressions (Regex).
* **Core Banking Transactions:**
  * **Cash Deposits & Withdrawals:** Real-time balance updates written directly to the database.
  * **Fast Cash:** Quick-selection preset withdrawal buttons for streamlined user experience.
  * **PIN Change:** Secure multi-table credential updates allowing users to safely modify their security PINs.
  * **Balance Inquiry:** Instant check of the latest available account balance.
  * **Mini Statement:** Detailed, columnar text-based preview of the last 5 account transactions.
* **Disconnected Architecture (`JdbcRowSet`):** Utilizes JDBC RowSets for flexible, scrollable, and updatable database interactions without keeping active database connections blocked indefinitely.

---

## 📸 Application Walkthrough & UI Showcase

### 1. Secure Login Portal
The entry point to the system featuring a modern split-screen layout for user authentication.
<p align="center">
  <img src="Images/Screenshot 2026-09-29 082236.png" alt="Login Screen" width="700"/>
</p>

---

### 2. Multi-Step User Registration (Signup)
A seamless guided registration flow collecting personal, demographic, and security credentials with regex validation.
* **Step 1: Personal Details**
  <p align="center">
    <img src="images/Screenshot 2026-09-29 082314.png" alt="Signup Personal Details" width="700"/>
  </p>

* **Step 2: Additional Background Details**
  <p align="center">
    <img src="images/Screenshot 2026-09-29 082400.png" alt="Signup Additional Details" width="700"/>
  </p>

* **Step 3: Account Type & Auto-Generated Credentials**
  <p align="center">
    <img src="images/Screenshot 2026-09-29 082857.png" alt="Signup Account Details" width="700"/>
  </p>

---

### 3. ATM Dashboard & Core Transactions
Once authenticated, users are greeted with an interactive ATM console to manage their funds.
* **Main ATM Dashboard:** Central hub for all banking operations.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 083023.png" alt="ATM Transactions Menu" width="700"/>
  </p>

* **Cash Deposit Screen:** Real-time balance reflection upon depositing funds.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 082946.png" alt="Deposit Screen" width="700"/>
  </p>

* **Cash Withdrawal Screen:** Secure cash dispensing mechanism with validation checks.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 083039.png" alt="Withdrawal Screen" width="700"/>
  </p>

* **Fast Cash Interface:** Preset buttons for rapid withdrawals.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 083057.png" alt="Fast Cash Screen" width="700"/>
  </p>

* **PIN Modification:** Secure interface for changing account security credentials.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 083131.png" alt="PIN Change Screen" width="700"/>
  </p>

* **Mini Statement Viewer:** Dialog output displaying recent account ledger activity.
  <p align="center">
    <img src="images/Screenshot 2026-09-29 083411.png" alt="Mini Statement" width="700"/>
  </p>

---

## 🛠️ Tech Stack

* **Language:** Java SE (JDK 8 or higher)
* **GUI Framework:** Java Swing (`JFrame`, `JLabel`, `JButton`, `JOptionPane`, etc.)
* **Database:** MySQL Server
* **Database Connectivity:** JDBC (`javax.sql.rowset.JdbcRowSet`)
* **IDE Compatibility:** Eclipse, IntelliJ IDEA, or NetBeans

---

## 📁 Project Structure

```text
com.bank/
│
├── Login.java                # Main entry point for user authentication
├── SignupFrame.java          # Multi-page user registration form with regex validation
├── Transactions.java         # Main ATM operations dashboard
├── Deposit.java              # Deposit amount handling window
├── Withdrawal.java           # Cash withdrawal processing window
├── FastCash.java             # Quick preset cash transaction window
├── PinChange.java            # Secure PIN modification window
└── (Additional utility classes & connection handlers)
