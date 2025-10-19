

## 🧪 Cómo usar el programa

1. **Ejecuta el archivo `Main.java`*CajeroApp* desde tu entorno de desarrollo (IDE).
2. El sistema simula el inicio de sesión del cliente y muestra un menú con opciones disponibles.
3. Selecciona la opción **“Retirar efectivo”**.
4. El sistema te pedirá que **selecciones un monto** (por ejemplo: $200, $500, $1000).
5. Luego, se mostrará el **estado de cuenta**, incluyendo:
   - Nombre del cliente
   - Saldo actual
   - Monto a retirar
   - Comisión aplicada
   - Saldo final estimado
6. Confirma la operación.
7. El sistema valida los fondos y ejecuta el retiro.
8. Finalmente, se genera un **recibo digital (ticker)** con los datos de la transacción:
   - Nombre del cliente
   - Monto retirado
   - Comisión
   - Últimos 4 dígitos de la cuenta
   - Saldo final

Este recibo se muestra en consola como comprobante de la operación.

---

## 🧩 Casos de Uso Implementados

1. Retirar efectivo  
2. Seleccionar monto  
3. Mostrar estado de cuenta  
4. Confirmar retiro  
5. Generar recibo  

Todos los casos están modelados en el archivo `.mdj` y documentados en el PDF.

---

## 🧠 Arquitectura MVC

- **Modelo:** Cliente, Cuenta, Transaccion  
- **Vista:** CajeroView  
- **Controlador:** CajeroController  

El controlador gestiona la lógica de retiro, validación de fondos y generación de recibo.

---

## 📄 Documentación

- `Examen_1_00000243421.pdf`: contiene especificaciones de casos de uso, storyboard, y ligas simuladas.
- `Examen_1_00000243421.mdj`: archivo StarUML con todos los diagramas del sistema.

---

## 🎥 Video Explicativo

[🔗 Ver video en YouTube](https://youtu.be/ejemplo123) *(liga simulada)*

---

## 🧠 Autor

**Yahir**  
Estudiante de Ingeniería en Software  
Universidad Instituto tecnológico de sonora
Octubre 2025