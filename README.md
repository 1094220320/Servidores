# TechGear API — Spring Boot + PostgreSQL

API REST para la tienda TechGear, conectada a tu base de datos local existente.

---

## ⚙️ Abrir en IntelliJ IDEA

1. **File → Open** → selecciona la carpeta `tienda-tech`
2. IntelliJ detecta automáticamente el `pom.xml` → click en **"Load Maven Project"**
3. Espera que descargue las dependencias (barra inferior)
4. Abre `src/main/resources/application.properties` y **ajusta tu contraseña** si es diferente a `postgres`
5. Corre `TechGearApplication.java` con el botón ▶️

> **Nota:** Si tu contraseña de PostgreSQL es diferente, edita esta línea:
> ```
> spring.datasource.password=TU_CONTRASEÑA
> ```

---

## 🗂️ Estructura del proyecto

```
tienda-tech/
├── src/main/java/com/techgear/
│   ├── TechGearApplication.java
│   ├── controller/          ← Endpoints REST
│   │   ├── CategoriaController.java
│   │   ├── ClienteController.java
│   │   ├── DispositivoController.java
│   │   └── FavoritoController.java
│   ├── service/             ← Lógica de negocio
│   ├── repository/          ← Acceso a la BD
│   ├── model/               ← Entidades JPA (mapean tus tablas)
│   ├── dto/                 ← Objetos de transferencia de datos
│   └── exception/           ← Manejo de errores
├── src/main/resources/
│   └── application.properties
├── Dockerfile
└── pom.xml
```

---

## 📡 Endpoints disponibles

### Categorías
| Método | URL | Descripción |
|--------|-----|-------------|
| GET    | `/api/v1/categorias` | Listar todas |
| GET    | `/api/v1/categorias/{id}` | Obtener por ID |
| POST   | `/api/v1/categorias` | Crear |
| PUT    | `/api/v1/categorias/{id}` | Actualizar |
| DELETE | `/api/v1/categorias/{id}` | Eliminar |
| GET    | `/api/v1/categorias/buscar?nombre=X` | Buscar |

### Clientes
| Método | URL | Descripción |
|--------|-----|-------------|
| GET    | `/api/v1/clientes` | Listar todos |
| GET    | `/api/v1/clientes/{id}` | Obtener por ID |
| POST   | `/api/v1/clientes` | Crear |
| PUT    | `/api/v1/clientes/{id}` | Actualizar |
| DELETE | `/api/v1/clientes/{id}` | Eliminar |
| GET    | `/api/v1/clientes/buscar?nombre=X` | Buscar |

### Dispositivos
| Método | URL | Descripción |
|--------|-----|-------------|
| GET    | `/api/v1/dispositivos` | Listar todos |
| GET    | `/api/v1/dispositivos/{id}` | Obtener por ID |
| POST   | `/api/v1/dispositivos` | Crear |
| PUT    | `/api/v1/dispositivos/{id}` | Actualizar |
| DELETE | `/api/v1/dispositivos/{id}` | Eliminar |
| GET    | `/api/v1/dispositivos/buscar?termino=X` | Buscar por modelo/marca/especificaciones |
| GET    | `/api/v1/dispositivos/categoria/{cateId}` | Filtrar por categoría |
| GET    | `/api/v1/dispositivos/marca/{marca}` | Filtrar por marca |

### Favoritos
| Método | URL | Descripción |
|--------|-----|-------------|
| GET    | `/api/v1/favoritos` | Listar todos |
| GET    | `/api/v1/favoritos/cliente/{clieId}` | Favoritos de un cliente |
| POST   | `/api/v1/favoritos` | Agregar favorito |
| DELETE | `/api/v1/favoritos/{favId}` | Eliminar por ID |
| DELETE | `/api/v1/favoritos/cliente/{clieId}/dispositivo/{disId}` | Eliminar por cliente+dispositivo |

---

## 📦 Ejemplos de peticiones

**Crear categoría:**
```json
POST /api/v1/categorias
{ "cateNombre": "Laptops", "cateDescripcion": "Portátiles y ultrabooks" }
```

**Crear dispositivo:**
```json
POST /api/v1/dispositivos
{
  "cateId": 1,
  "disModelo": "ThinkPad X1 Carbon",
  "disMarca": "Lenovo",
  "disEspecificaciones": "Intel Core i7, 16GB RAM, 512GB SSD"
}
```

**Agregar favorito:**
```json
POST /api/v1/favoritos
{ "clieId": 1, "disId": 3 }
```

---

## 🐳 Crear imagen Docker y subirla

```bash
# 1. Construir imagen
docker build -t techgear-api:1.0.0 .

# 2. Etiquetar con tu usuario de Docker Hub
docker tag techgear-api:1.0.0 TU_USUARIO_DOCKERHUB/techgear-api:1.0.0

# 3. Iniciar sesión
docker login

# 4. Subir
docker push TU_USUARIO_DOCKERHUB/techgear-api:1.0.0

# 5. Correr la imagen (conectando a tu PostgreSQL local)
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_NAME=TechGear \
  -e DB_USER=postgres \
  -e DB_PASSWORD=TU_CONTRASEÑA \
  techgear-api:1.0.0
```

> **Tip:** `host.docker.internal` permite que el contenedor Docker alcance PostgreSQL en tu PC local (Windows/Mac). En Linux usa `--network host`.
"# tienda-tech" 
