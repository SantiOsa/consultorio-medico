# 🏥 Sistema de Gestão de Consultório Médico

Sistema completo de gestão de consultório médico desenvolvido como projeto acadêmico, permitindo agendamento de consultas, gerenciamento de pacientes e médicos com autenticação segura.

![Java](https://img.shields.io/badge/Kotlin-1.8.22-purple?style=for-the-badge&logo=kotlin)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green?style=for-the-badge&logo=spring)
![React](https://img.shields.io/badge/React-19.1.0-blue?style=for-the-badge&logo=react)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge&logo=mysql)

## 📋 Sobre o Projeto

Este sistema foi desenvolvido para facilitar a gestão de consultas médicas, oferecendo interfaces distintas para pacientes e médicos. O projeto demonstra a implementação de uma aplicação full-stack moderna com autenticação JWT, validação de dados e uma arquitetura bem estruturada.

### ✨ Funcionalidades Principais

#### Para Pacientes 👤
- ✅ Cadastro e autenticação de usuários
- 📅 Visualização de médicos disponíveis por especialização
- 🗓️ Agendamento de consultas
- 📋 Histórico de consultas agendadas
- ❌ Cancelamento de consultas
- 👨‍⚕️ Visualização de perfil

#### Para Médicos 👨‍⚕️
- ✅ Cadastro e autenticação
- 📊 Dashboard com visão geral de consultas
- 👥 Listagem de pacientes atendidos
- 📅 Gerenciamento de agenda
- ❌ Cancelamento de consultas
- 📝 Visualização de perfil

### 🔐 Segurança
- Autenticação via JWT (JSON Web Tokens)
- Senhas criptografadas
- Rotas protegidas no backend
- Validação de dados no frontend e backend
- CORS configurado para segurança de API

## 🛠️ Tecnologias Utilizadas

### Backend
- **Kotlin** - Linguagem de programação
- **Spring Boot 3.1.5** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **JWT (JSON Web Tokens)** - Autenticação stateless
- **MySQL** - Banco de dados relacional
- **Gradle** - Gerenciador de dependências

### Frontend
- **React 19.1.0** - Biblioteca JavaScript
- **React Router DOM** - Roteamento SPA
- **React Bootstrap** - Componentes UI
- **Axios** - Cliente HTTP
- **Bootstrap 5** - Estilização

## 📁 Estrutura do Projeto

```
consultorio/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/consultorio/
│   │   │   │   ├── ConsultorioApplication.kt
│   │   │   │   ├── config/          # Configurações (Security, CORS)
│   │   │   │   ├── controller/      # Controllers REST
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── exception/       # Tratamento de exceções
│   │   │   │   ├── model/           # Entidades JPA
│   │   │   │   ├── repository/      # Repositórios
│   │   │   │   ├── security/        # JWT e segurança
│   │   │   │   └── service/         # Lógica de negócio
│   │   │   └── resources/
│   │   │       ├── application.yml  # Configurações principais
│   │   │       └── schema.sql       # Script SQL
│   │   └── test/                    # Testes unitários
│   ├── build.gradle                 # Dependências Gradle
│   └── gradlew                      # Gradle Wrapper
│
└── frontend/
    ├── public/
    │   └── index.html
    ├── src/
    │   ├── components/              # Componentes reutilizáveis
    │   │   ├── Header.js
    │   │   ├── MedicoCard.js
    │   │   ├── ConsultasList.js
    │   │   └── AgendarConsultaModal.js
    │   ├── pages/                   # Páginas principais
    │   │   ├── Login.js
    │   │   ├── Register.js
    │   │   ├── PacienteDashboard.js
    │   │   ├── MedicoDashboard.js
    │   │   └── Perfil.js
    │   ├── services/                # Serviços de API
    │   │   ├── api.js
    │   │   ├── auth.service.js
    │   │   ├── consulta.service.js
    │   │   └── user.service.js
    │   ├── utils/                   # Utilitários
    │   ├── App.js                   # Componente principal
    │   └── index.js                 # Entry point
    └── package.json                 # Dependências NPM
```

## 🚀 Como Executar o Projeto

### Pré-requisitos

- **Java JDK 17** ou superior
- **Node.js 16** ou superior
- **MySQL 8.0** ou superior
- **Git**

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/consultorio-medico.git
cd consultorio-medico
```

### 2. Configuração do Banco de Dados

Crie o banco de dados MySQL:

```sql
CREATE DATABASE consultorio;
```

Execute o script SQL localizado em `backend/src/main/resources/schema.sql` ou deixe o Hibernate criar as tabelas automaticamente (configurado com `ddl-auto: update`).

### 3. Configuração do Backend

**Configure as credenciais do banco de dados:**

Edite o arquivo `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/consultorio?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: seu_usuario
    password: sua_senha
```

**Execute o backend:**

```bash
cd backend

# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

O backend estará disponível em: `http://localhost:8080`

### 4. Configuração do Frontend

**Instale as dependências:**

```bash
cd frontend
npm install
```

**Execute o frontend:**

```bash
npm start
```

O frontend estará disponível em: `http://localhost:3000`

## 📡 API Endpoints

### Autenticação
- `POST /api/auth/register` - Cadastro de novo usuário
- `POST /api/auth/login` - Login de usuário

### Pacientes
- `GET /api/pacientes` - Listar todos os pacientes
- `GET /api/pacientes/{id}` - Buscar paciente por ID
- `PUT /api/pacientes/{id}` - Atualizar dados do paciente
- `DELETE /api/pacientes/{id}` - Deletar paciente

### Médicos
- `GET /api/medicos` - Listar todos os médicos
- `GET /api/medicos/{id}` - Buscar médico por ID
- `PUT /api/medicos/{id}` - Atualizar dados do médico
- `DELETE /api/medicos/{id}` - Deletar médico

### Consultas
- `GET /api/consultas` - Listar todas as consultas
- `GET /api/consultas/paciente/{pacienteId}` - Consultas de um paciente
- `GET /api/consultas/medico/{medicoId}` - Consultas de um médico
- `POST /api/consultas` - Agendar nova consulta
- `DELETE /api/consultas/{id}` - Cancelar consulta

## 🗃️ Modelo de Dados

### Paciente
```kotlin
- id: Long (PK)
- nome: String
- email: String (unique)
- senha: String (encrypted)
- consultas: List<Consulta>
```

### Médico
```kotlin
- id: Long (PK)
- nome: String
- email: String (unique)
- senha: String (encrypted)
- especializacao: String
- consultas: List<Consulta>
```

### Consulta
```kotlin
- id: Long (PK)
- paciente: Paciente (FK)
- medico: Medico (FK)
- data: LocalDate
- hora: LocalTime
```

## 🧪 Testando a Aplicação

### Usuários de Teste

Após executar a aplicação, você pode criar usuários através da tela de registro ou inserir dados diretamente no banco:

**Médico de Exemplo:**
```sql
INSERT INTO medicos (nome, email, senha, especializacao) 
VALUES ('Dr. João Silva', 'joao@exemplo.com', '$2a$10$...', 'Cardiologia');
```

**Paciente de Exemplo:**
```sql
INSERT INTO pacientes (nome, email, senha) 
VALUES ('Maria Santos', 'maria@exemplo.com', '$2a$10$...');
```

> **Nota:** As senhas devem ser criptografadas com BCrypt. Use a funcionalidade de registro da aplicação para criar usuários com senhas corretamente criptografadas.

## 🎯 Funcionalidades Implementadas

- [x] Sistema de autenticação com JWT
- [x] CRUD completo de Pacientes
- [x] CRUD completo de Médicos
- [x] CRUD completo de Consultas
- [x] Dashboard para Pacientes
- [x] Dashboard para Médicos
- [x] Validação de dados
- [x] Tratamento de erros
- [x] Responsive design
- [x] Segurança com Spring Security

## 🔮 Possíveis Melhorias Futuras

- [ ] Sistema de notificações por email
- [ ] Lembretes de consulta
- [ ] Histórico médico do paciente
- [ ] Upload de documentos/exames
- [ ] Sistema de avaliação de médicos
- [ ] Integração com calendário (Google Calendar, Outlook)
- [ ] Relatórios e estatísticas
- [ ] Chat entre médico e paciente
- [ ] Videoconferência para teleconsulta

## 📝 Licença

Este projeto foi desenvolvido para fins acadêmicos e está disponível sob a licença MIT.

## 👨‍💻 Autor

**Santiago de La Osa de Sousa**

- GitHub: [SantiOsa](https://github.com/SantiOsa)
- LinkedIn: [santiosa](https://www.linkedin.com/in/santiosa/)

## 🤝 Contribuições

Contribuições, issues e feature requests são bem-vindos! Sinta-se à vontade para verificar a página de issues.

## ⭐ Mostre seu apoio

Se este projeto te ajudou de alguma forma, considere dar uma ⭐️!

---

**Desenvolvido com ❤️ como projeto acadêmico**
