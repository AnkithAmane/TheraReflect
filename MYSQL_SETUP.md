# MySQL Database Setup for TheraReflect

## Prerequisites
1. Install MySQL Server (8.0 or higher recommended)
2. Install MySQL Workbench or use MySQL Command Line Client

## Database Setup Instructions

### Step 1: Create Database
```sql
-- Connect to MySQL as root user
-- Create database
CREATE DATABASE therapereflect_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE therapereflect_db;
```

### Step 2: Create Tables
The application will automatically create tables, but if you want to create them manually:

```sql
-- Create Patient table
CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Therapist table
CREATE TABLE therapist (
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
CREATE TABLE journal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    text TEXT,
    ai_analysis TEXT,
    date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

-- Create Request table (for therapist-patient connections)
CREATE TABLE request (
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
```

### Step 3: Create Indexes for Performance
```sql
-- Create indexes for better performance
CREATE INDEX idx_patient_email ON patient(email);
CREATE INDEX idx_therapist_email ON therapist(email);
CREATE INDEX idx_journal_patient ON journal(patient_id);
CREATE INDEX idx_journal_date ON journal(date);
CREATE INDEX idx_request_patient ON request(patient_id);
CREATE INDEX idx_request_therapist ON request(therapist_id);
CREATE INDEX idx_request_status ON request(status);
```

### Step 4: Create Database User (Optional, for security)
```sql
-- Create dedicated user for the application
CREATE USER 'therapereflect_user'@'localhost' IDENTIFIED BY 'your_secure_password';

-- Grant necessary privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON therapereflect_db.* TO 'therapereflect_user'@'localhost';

-- Flush privileges
FLUSH PRIVILEGES;
```

## Configuration in application.properties

Update your application.properties file with:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/therapereflect_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Connection Pool Settings
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000

# Server Configuration
server.port=8081
```

## Important Notes

1. **Password**: Change 'yourpassword' to your actual MySQL root password
2. **Database Name**: 'therapereflect_db' (can be changed if needed)
3. **Port**: MySQL default port is 3306
4. **Auto Schema**: Set to 'update' so tables are created/updated automatically
5. **Character Set**: UTF8MB4 for full Unicode support

## Troubleshooting

### Common Issues:
1. **Connection Error**: Check if MySQL service is running
2. **Access Denied**: Verify username/password in application.properties
3. **Database Not Found**: Ensure 'therapereflect_db' database exists
4. **Port Conflict**: Make sure MySQL is running on port 3306

### Commands to Check MySQL Status:
```bash
# Windows (Command Prompt as Administrator)
net start mysql80  # Start MySQL service
net stop mysql80   # Stop MySQL service

# Check if MySQL is running
netstat -an | findstr :3306
```

### Test Connection:
```bash
mysql -u root -p
USE therapereflect_db;
SHOW TABLES;
```

## Sample Data (Optional)
```sql
-- Insert sample patient
INSERT INTO patient (name, email, password) 
VALUES ('John Doe', 'john.doe@gmail.com', 'hashed_password_here');

-- Insert sample therapist
INSERT INTO therapist (name, email, password, specialty, availability) 
VALUES ('Dr. Jane Smith', 'dr.jane@gmail.com', 'hashed_password_here', 'Clinical Psychology', 'Mon-Fri 9AM-5PM');
```