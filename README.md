# 🎮 My Game Room

**My Game Room** es una aplicación Android para descubrir, explorar y gestionar una colección personal de videojuegos.

La aplicación permite explorar videojuegos, consultar información detallada, guardar juegos como favoritos y gestionar una colección personal desde un mismo lugar.

Este proyecto nace como proyecto personal para practicar y aplicar desarrollo Android nativo utilizando **Kotlin y Jetpack Compose**.

## ✨ Funcionalidades

* 🔎 Descubrir y explorar videojuegos
* ❤️ Añadir y eliminar videojuegos de favoritos
* 🌐 Obtener información de videojuegos mediante la **API de IGDB**
* 🔐 Autenticación mediante **OAuth 2.0**
* 🖼️ Mostrar imágenes y carátulas de videojuegos
* 📱 Interfaz desarrollada con **Material 3**
* 💾 Persistencia de datos local mediante **Room**
* ⚡ Operaciones asíncronas mediante **Kotlin Coroutines**

## 🛠️ Tecnologías utilizadas

### Lenguaje e interfaz

* **Kotlin**
* **Jetpack Compose**
* **Material 3**

### Arquitectura

* **MVVM**
* **ViewModel**
* Gestión de estado de la UI
* **Repository Pattern**
* Separación entre interfaz, lógica y acceso a datos

### Android y librerías

* **Android SDK**
* **Jetpack Compose**
* **Navigation Compose**
* **Room**
* **Retrofit**
* **OkHttp**
* **Coil**
* **Kotlin Coroutines**

### API y datos

* **IGDB API**
* **OAuth 2.0**
* REST
* JSON
* Base de datos local con Room

### Herramientas

* **Android Studio**
* **Gradle**
* **Git**
* **GitHub**

## 🏗️ Arquitectura

La aplicación utiliza una arquitectura basada en **MVVM**, junto con una capa Repository encargada de coordinar el acceso a las diferentes fuentes de datos.

```text
UI — Jetpack Compose
        │
        ▼
   ViewModel
        │
        ▼
   Repository
      /   \
     /     \
    ▼       ▼
 API       Room
```

La interfaz observa el estado expuesto por los `ViewModel`, mientras que el `Repository` se encarga de coordinar el acceso a los datos remotos y locales.

Esta estructura permite mantener las diferentes responsabilidades separadas y facilita la evolución del proyecto.

## 🌐 IGDB API

**My Game Room** utiliza la **IGDB API** para obtener información sobre videojuegos, como títulos, descripciones, plataformas, géneros e imágenes.

El acceso a la API requiere autenticación mediante **OAuth 2.0**.

La comunicación con la API se realiza mediante **Retrofit** y **OkHttp**.

## 💾 Almacenamiento local

La aplicación utiliza **Room** para almacenar información localmente en el dispositivo.

De esta forma, determinados datos pueden mantenerse disponibles localmente sin depender completamente de la conexión con la API.

## 📸 Capturas de pantalla

<p align="center">
  <img src="screenshots/home.png" width="220">
  <img src="screenshots/explore.png" width="220">
  <img src="screenshots/detail.png" width="220">
  <img src="screenshots/favorites.png" width="220">
</p>

## 🚀 Instalación

### Requisitos

* Android Studio
* JDK
* Android SDK
* Configuración de desarrollador necesaria para utilizar la API de IGDB

### Pasos

1. Clona el repositorio:

```bash
git clone https://github.com/Azahara-dev/MyGameRoom.git
```

2. Abre el proyecto en Android Studio.

3. Configura las credenciales necesarias para la API.

4. Sincroniza el proyecto con Gradle.

5. Ejecuta la aplicación en un dispositivo Android o emulador.

> Las credenciales de la API no deben incluirse directamente en el repositorio.

## 🎯 Objetivos de aprendizaje

Este proyecto forma parte de mi proceso de especialización en **desarrollo Android nativo** y me ha permitido practicar:

* Kotlin
* Jetpack Compose
* Arquitectura de aplicaciones Android
* Gestión de estados
* ViewModel
* Repository Pattern
* Consumo de APIs REST
* Autenticación OAuth 2.0
* Persistencia local con Room
* Kotlin Coroutines
* Gestión de dependencias
* Git y GitHub
* Desarrollo de una aplicación Android de principio a fin

## 🔮 Próximas mejoras

Algunas funcionalidades que podrían incorporarse en futuras versiones:

* 🔎 Filtros y opciones de búsqueda más avanzadas
* 📊 Mejor gestión de la colección personal
* 📶 Mayor soporte para funcionamiento offline
* 🎮 Más información sobre cada videojuego
* 🎨 Mejoras de UI/UX
* 🧪 Ampliación de los tests automatizados
* ⚡ Mejoras de rendimiento y arquitectura

## 👩‍💻 Sobre el proyecto

**My Game Room** es un proyecto personal desarrollado para consolidar mis conocimientos de desarrollo Android y aplicar tecnologías modernas en una aplicación funcional.

El proyecto combina mi experiencia previa en desarrollo de software con mi actual enfoque en **Kotlin y desarrollo Android nativo**.
