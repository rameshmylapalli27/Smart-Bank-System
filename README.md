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
  * **Balance Inquiry:** Instant check of the latest available account balance.
  * **Mini Statement:** Detailed, columnar text-based preview of the last 5 account transactions.
* **Disconnected Architecture (`JdbcRowSet`):** Utilizes JDBC RowSets for flexible, scrollable, and updatable database interactions without keeping active database connections blocked indefinitely.

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
├── SignupFrame.java          # User registration form with regex validation
├── Transactions.java         # Main ATM operations dashboard
├── Deposit.java              # Deposit amount handling window
├── Withdrawal.java           # Cash withdrawal processing window
├── FastCash.java             # Quick preset cash transaction window
├── PinChange.java            # Secure PIN modification window
└── (Additional utility classes & connection handlers)
