# 🚀 Guia para Publicar no GitHub

Este guia vai te ajudar a publicar seu projeto no GitHub de forma profissional.

## 📝 Antes de Publicar

### 1. Verifique as configurações sensíveis

**IMPORTANTE:** Não commite senhas ou dados sensíveis!

- ✅ O arquivo `application.yml.example` foi criado como modelo
- ✅ O arquivo `application.yml` com suas senhas está no `.gitignore`
- ✅ Certifique-se de que não há senhas no código

### 2. Atualize suas informações no README.md

Edite as seguintes seções no `README.md`:

```markdown
## 👨‍💻 Autor

**Santiago de La Osa de Sousa**

- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [Seu Nome](https://linkedin.com/in/seu-perfil)
```

## 🎯 Passos para Criar o Repositório

### 1. Crie o repositório no GitHub

1. Acesse [github.com](https://github.com)
2. Clique no botão **"+"** → **"New repository"**
3. Preencha:
   - **Repository name:** `consultorio-medico` (ou outro nome)
   - **Description:** "Sistema de gestão de consultório médico com Spring Boot e React"
   - **Visibility:** Public (para portfólio)
   - ⚠️ **NÃO** marque "Initialize with README" (já temos um)

### 2. Configure o Git local

Abra o PowerShell na pasta do projeto e execute:

```powershell
# Inicializa o repositório Git
git init

# Adiciona todos os arquivos
git add .

# Faz o primeiro commit
git commit -m "feat: Initial commit - Sistema de Consultório Médico"

# Renomeia a branch para main
git branch -M main

# Adiciona o repositório remoto (substitua SEU-USUARIO e NOME-DO-REPO)
git remote add origin https://github.com/SEU-USUARIO/NOME-DO-REPO.git

# Envia para o GitHub
git push -u origin main
```

### 3. Configure o repositório no GitHub

Após o push, acesse seu repositório no GitHub e configure:

#### Adicione Topics (Tags)
Clique em "⚙️ About" → "⚙️" → Adicione topics:
- `spring-boot`
- `kotlin`
- `react`
- `mysql`
- `jwt`
- `rest-api`
- `healthcare`
- `medical-system`

#### Adicione uma descrição
"Sistema completo de gestão de consultório médico com agendamento de consultas, desenvolvido com Spring Boot (Kotlin) e React"

## 🎨 Melhorando seu Repositório

### Adicione Screenshots

Crie uma pasta `docs/screenshots` e adicione imagens:

```powershell
mkdir docs\screenshots
```

Tire prints das telas principais:
- Login
- Dashboard do Paciente
- Dashboard do Médico
- Agendamento de consulta

Adicione no README após a descrição:

```markdown
## 📸 Screenshots

### Tela de Login
![Login](docs/screenshots/login.png)

### Dashboard do Paciente
![Dashboard Paciente](docs/screenshots/paciente-dashboard.png)

### Dashboard do Médico
![Dashboard Médico](docs/screenshots/medico-dashboard.png)
```

### Configure o GitHub Pages (opcional)

Se quiser hospedar a documentação:

1. Settings → Pages
2. Source: Deploy from a branch
3. Branch: main → /docs
4. Save

## 🔒 Segurança

### ⚠️ NUNCA commite:

- ❌ Senhas do banco de dados
- ❌ Chaves JWT secretas
- ❌ Tokens de API
- ❌ Arquivos `.env` com dados sensíveis

### ✅ SEMPRE:

- ✅ Use variáveis de ambiente
- ✅ Mantenha arquivos `.example` como modelo
- ✅ Documente como configurar as variáveis

## 📊 Melhorando a Visibilidade

### 1. Adicione badges extras no README

```markdown
![License](https://img.shields.io/badge/license-MIT-green)
![GitHub repo size](https://img.shields.io/github/repo-size/seu-usuario/consultorio-medico)
![GitHub last commit](https://img.shields.io/github/last-commit/seu-usuario/consultorio-medico)
```

### 2. Crie releases

Após ajustes:

```powershell
git tag -a v1.0.0 -m "Versão inicial do projeto"
git push origin v1.0.0
```

### 3. Escreva commits descritivos

Use conventional commits:
```
feat: adiciona nova funcionalidade
fix: corrige bug específico
docs: atualiza documentação
style: melhora formatação
refactor: refatora código
test: adiciona testes
```

## 🎯 Checklist Final

Antes de compartilhar no LinkedIn/portfólio:

- [ ] README.md completo e personalizado
- [ ] .gitignore configurado corretamente
- [ ] Sem dados sensíveis no código
- [ ] Código comentado e organizado
- [ ] Topics/tags adicionadas no GitHub
- [ ] Descrição clara do repositório
- [ ] Screenshots adicionadas (opcional)
- [ ] License adicionada (MIT recomendada)

## 🎓 Para Mencionar no LinkedIn

Sugestão de post:

```
🚀 Novo projeto no portfólio!

Desenvolvi um Sistema de Gestão de Consultório Médico completo durante meu período acadêmico.

🔧 Tecnologias:
• Backend: Kotlin + Spring Boot + JWT
• Frontend: React + Bootstrap
• Database: MySQL
• Segurança: Spring Security

✨ Principais features:
• Autenticação com JWT
• Dashboard para médicos e pacientes
• Agendamento de consultas
• API RESTful
• Design responsivo

Confira o código no GitHub: [link]

#SpringBoot #React #Kotlin #WebDevelopment #FullStack
```

## 🆘 Problemas Comuns

### Erro: "remote origin already exists"
```powershell
git remote remove origin
git remote add origin https://github.com/SEU-USUARIO/REPO.git
```

### Erro ao fazer push
```powershell
git pull origin main --allow-unrelated-histories
git push -u origin main
```

### Esquecer de adicionar .gitignore
```powershell
git rm -r --cached .
git add .
git commit -m "fix: adiciona .gitignore"
git push
```

---

**Boa sorte com seu repositório! 🚀**
