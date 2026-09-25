## Habilitar Lombok en tu IDE

El proyecto utiliza **Lombok** para auto-generar getters y setters.

* **En IntelliJ IDEA:**
  1. Ve a `File` ➔ `Settings` (o `Ctrl + Alt + S`).
  2. Navega a `Build, Execution, Deployment` ➔ `Compiler` ➔ `Annotation Processors`.
  3. Marca la casilla **"Enable annotation processing"**.
  4. Guarda con **Apply** y **OK**.

---

### Desde IntelliJ IDEA
1. Abre IntelliJ IDEA y selecciona **File ➔ Open**.
2. Selecciona la carpeta del proyecto: `OOP/Asistencia`.
3. Espera a que el IDE cargue y descargue las dependencias de Maven.
4. Navega en la estructura del proyecto hasta:
   `src/main/java/pe/edu/upeu/asistencia/Aplicacion.java`
5. Haz clic derecho sobre `Aplicacion.java` y selecciona **Run 'Aplicacion.main()'** (o presiona `Shift + F10`).
6. En la ventana principal de la aplicación, haz clic en el botón **`👥 Abrir CRUD Participantes`**.

### Desde la Consola de Comandos (CMD / PowerShell)
1. Abre la terminal y navega hasta la carpeta del proyecto:
   ```cmd
   cd Asistencia
   ```
2. Ejecuta el comando de Spring Boot:
   ```cmd
   .\mvnw spring-boot:run
   ```
   *(o si instalaste Maven globalmente: `mvn spring-boot:run`)*

---

## Cómo Ejecutar el Proyecto `BaiscConceptsPOO`

###Desde IntelliJ IDEA
1. Selecciona **File ➔ Open** y elige la carpeta `OOP/BaiscConceptsPOO`.
2. Navega a:
   `src/main/java/pe/edu/upeu/encapsulamiento/ClaseGeneral.java`
3. Haz clic derecho y selecciona **Run 'ClaseGeneral.main()'**.

### Desde la Consola
1. Navega a la carpeta:
   ```cmd
   cd BaiscConceptsPOO
   ```
2. Compila y ejecuta:
   ```cmd
   mvn compile
   mvn exec:java "-Dexec.mainClass=pe.edu.upeu.encapsulamiento.ClaseGeneral"
   ```

---


## 

| Problema / Error | Causa | Solución |
| :--- | :--- | :--- |
| `mvn no se reconoce como un comando...` | Maven no está agregado al PATH de Windows. | Utiliza `.\mvnw` desde la carpeta del proyecto o agrega la carpeta `bin` de Maven a las Variables de Entorno `PATH`. |
