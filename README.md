# TheraReflect - Mental Health Platform

TheraReflect is a comprehensive mental health platform that connects patients with licensed therapists, providing a safe space for mental health support and therapy services.

## 🌟 Features

### 👥 User Management
- **Patient Registration & Login**: Secure signup and authentication for patients
- **Therapist Registration & Login**: Professional registration with specialty and availability
- **Dual User Types**: Separate dashboards and functionalities for patients and therapists
- **Secure Authentication**: Custom password hashing with SHA-256 and salt

### 💻 Web Interface
- **Responsive Design**: Modern, mobile-friendly interface using Bootstrap 5
- **Interactive Forms**: User-friendly signup and login forms with validation
- **Dashboard Views**: Personalized dashboards for both patients and therapists
- **Seamless Navigation**: Intuitive user experience across all pages

### 🗄️ Database Integration
- **MySQL Database**: Persistent data storage with proper relationships
- **JPA/Hibernate**: Object-relational mapping for efficient data management
- **Data Validation**: Input validation and constraints at database level
- **Scalable Architecture**: Ready for production deployment

## 🚀 Technology Stack

- **Backend**: Spring Boot 3.2.5, Java 17+
- **Frontend**: Thymeleaf, HTML5, CSS3, Bootstrap 5
- **Database**: MySQL 8.0+
- **Security**: Custom authentication system
- **Build Tool**: Maven
- **ORM**: JPA/Hibernate

## 📁 Project Structure

```
TheraReflect/
├── src/
│   ├── main/
│   │   ├── java/com/example/mentalhealth/
│   │   │   ├── controller/          # Web controllers
│   │   │   ├── entity/             # Database entities
│   │   │   ├── repository/         # Data access layer
│   │   │   ├── service/            # Business logic
│   │   │   ├── dto/                # Data transfer objects
│   │   │   ├── util/               # Utility classes
│   │   │   └── MentalHealthApplication.java
│   │   └── resources/
│   │       ├── templates/          # Thymeleaf templates
│   │       ├── static/css/         # Stylesheets
│   │       └── application.properties
├── .gitignore
├── pom.xml
├── MYSQL_SETUP.md
├── setup_database.sql
└── README.md
```

## 🛠️ Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

### Database Setup
1. Install MySQL and create database:
```sql
CREATE DATABASE therapereflect_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Run the setup script:
```bash
mysql -u root -p < setup_database.sql
```

### Application Setup
1. Clone the repository:
```bash
git clone https://github.com/AnkithAmane/TheraReflect.git
cd TheraReflect
```

2. Update database configuration in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/therapereflect_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Access the application at: `http://localhost:8081`

## 🎯 Usage

### For Patients:
1. Visit the application homepage
2. Click "Sign Up" and select "Patient"
3. Fill in your details (name, email, password)
4. Login with your credentials
5. Access your personalized patient dashboard

### For Therapists:
1. Visit the application homepage
2. Click "Sign Up" and select "Therapist"
3. Fill in your details including specialty and availability
4. Login with your credentials
5. Access your professional therapist dashboard

## 🗃️ Database Schema

### Tables:
- **patient**: Patient user information
- **therapist**: Therapist user information and professional details
- **journal**: Patient journal entries (for future features)
- **request**: Therapist-patient connection requests (for future features)

## 🔐 Security Features

- Password hashing using SHA-256 with random salt
- Email uniqueness validation
- Input validation and sanitization
- Session-based authentication
- SQL injection prevention through JPA

## 🚀 Future Enhancements

- [ ] Journal writing functionality for patients
- [ ] Appointment scheduling system
- [ ] Real-time messaging between patients and therapists
- [ ] AI-powered mental health insights
- [ ] Payment integration
- [ ] Mobile app development

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Ankith Amane** - [AnkithAmane](https://github.com/AnkithAmane)

## 📧 Contact

For any queries or support, please reach out through GitHub issues or contact the maintainer.

---

⭐ Star this repository if you find it helpful!