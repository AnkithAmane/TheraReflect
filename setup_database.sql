-- TheraReflect MySQL Database Setup Script
-- Run this script in MySQL Workbench or MySQL Command Line Client

-- Create database
CREATE DATABASE IF NOT EXISTS therapereflect_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE therapereflect_db;

-- Create Patient table
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Therapist table
CREATE TABLE IF NOT EXISTS therapist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    specialty VARCHAR(255),
    availability VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Journal table (for future use)
CREATE TABLE IF NOT EXISTS journal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    text TEXT,
    ai_analysis TEXT,
    date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

-- Create Request table (for therapist-patient connections)
CREATE TABLE IF NOT EXISTS request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    therapist_id BIGINT NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING',
    summary TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (therapist_id) REFERENCES therapist(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_patient_email ON patient(email);
CREATE INDEX IF NOT EXISTS idx_therapist_email ON therapist(email);
CREATE INDEX IF NOT EXISTS idx_journal_patient ON journal(patient_id);
CREATE INDEX IF NOT EXISTS idx_journal_date ON journal(date);
CREATE INDEX IF NOT EXISTS idx_request_patient ON request(patient_id);
CREATE INDEX IF NOT EXISTS idx_request_therapist ON request(therapist_id);
CREATE INDEX IF NOT EXISTS idx_request_status ON request(status);

-- Show created tables
SHOW TABLES;

-- Display table structures
DESCRIBE patient;
DESCRIBE therapist;
DESCRIBE journal;
DESCRIBE request;