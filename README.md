Nombre Del Proyecto: Jhoflix 1.0.0 para Android Tv - UTCAM - 2026

Nombres del Equipo: "Equipo 2"
Manuel Alfonso Castro Escalante
Mirley Madai Gómez Acosta
Karla Guadalupe Madrazo Can
Flor Yazmín Cordero Estañol

Descripción del proyecto:

Plataforma de streaming local para Android TV. Los videos se almacenan en una computadora que actúa como servidor dentro de la misma red Wi-Fi/LAN, y la app reproduce el contenido vía streaming HTTP, sin necesidad de descargar los archivos completos al dispositivo.

Arquitectura utilizada:

●Arquitectura general del sistema

PC / Servidor (Windows)
      │
      ▼
Servidor Python + FastAPI
      │
      ▼
Carpetas /videos y /posters
      │
      ▼
   Red Wi-Fi / LAN
      │
      ▼
  Jhoflix (Android TV)
      │
      ▼
AndroidX Media3 ExoPlayer
      │
      ▼
  Reproducción del video

●Estructura de paquetes

com.manuel.jhoflix
│
├── data
│   ├── model          → modelos de datos (Video, etc.)
│   ├── remote          → definición de endpoints Retrofit
│   ├── repository      → VideoRepository (lógica de acceso a datos)
│   └── local            → SettingsDataStore (persistencia de la IP del servidor)
│
├── ui
│   ├── home             → HomeScreen, HomeUiState
│   ├── player            → pantalla del reproductor
│   ├── settings          → pantalla de configuración del servidor
│   └── components        → MovieCard y otros componentes reutilizables
│
├── viewmodel             → HomeViewModel, SettingsViewModel
│
├── navigation             → NavHost y rutas de la app
│
└── MainActivity.kt

Tecnologías utilizadas:

Cómo ejecutar el servidor.

1. Asegúrate de tener Python instalado en el equipo que hará de servidor.

2. Coloca tus archivos de video dentro de la carpeta jhoflix-server/videos y sus portadas (con el mismo nombre de archivo) dentro de jhoflix-server/posters.

3. Instala las dependencias necesarias (FastAPI, Uvicorn, etc.) si es la primera vez.

4. Inicia el servidor de alguna de estas dos formas: Manualmente:

Cómo configurar la dirección del servidor en la aplicación:

1. Asegúrate de tener Python instalado en el equipo que hará de servidor.

2. Coloca tus archivos de video dentro de la carpeta jhoflix-server/videos y sus portadas (con el mismo nombre de archivo) dentro de jhoflix-server/posters.

3. Instala las dependencias necesarias (FastAPI, Uvicorn, etc.) si es la primera vez.

4. Inicia el servidor Manualmente:

   cd C:\Users\RUTA DEL ARCHIVO\jhoflix-server
   python server.py

5. La consola mostrará la IP local y el puerto donde quedó escuchando, por ejemplo:

   ================================
           JHOFLIX SERVER
   ================================
   Servidor iniciado
   IP: 192.168.1.81
   Puerto: 5000
   URL: http://192.168.1.81:5000

Cómo ejecutar la aplicación:

Requisitos: Android Studio, un dispositivo o emulador de Android TV con API 28+ (idealmente API 36), y estar en la misma red Wi-Fi/LAN que el servidor.

1. Abre el proyecto Jhoflix en Android Studio.

2. Espera a que Gradle sincronice correctamente (Sync Project with Gradle Files).

3. Selecciona un dispositivo Android TV (físico, conectado por USB/ADB, o un emulador de tipo Television en el AVD Manager).

4. Ejecuta la app con el botón ▶️ Run.

5. En el primer inicio, configura la dirección del servidor como se indica en la sección anterior.

6. Navega el catálogo con el D-pad del control remoto (↑ ↓ ← →), selecciona una película con OK, y usa Back para regresar.

7. Importante: el servidor Python debe estar corriendo antes de abrir o actualizar el catálogo en la app, ya que Jhoflix depende de una conexión activa a la API para mostrar las películas.

Evidencia:

●Servidor

<img width="1084" height="603" alt="ChatGPT Image 17 ago 2026, 10_50_33 p m" src="https://github.com/user-attachments/assets/e8b8da33-2832-4c4d-b318-3a44ebeca7cb" />


●Catálogo

<img width="1671" height="1029" alt="Captura de pantalla 2026-08-17 224534" src="https://github.com/user-attachments/assets/19528df7-93f3-4886-9c55-08fe6d12abd6" />
<img width="1678" height="1025" alt="Captura de pantalla 2026-08-17 225258" src="https://github.com/user-attachments/assets/693cd243-bdbd-4113-9dbd-20fc243985b9" />

●Reproduccíon

<img width="1669" height="1027" alt="Captura de pantalla 2026-08-17 225339" src="https://github.com/user-attachments/assets/508f9783-641e-449e-beac-db47132916f2" />
