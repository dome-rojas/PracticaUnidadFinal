package controlador;

import java.text.DecimalFormat;

public class VentasGastos {
    private Double[][] datosMensuales;

    public VentasGastos() {
        datosMensuales = new Double[12][2]; // Usamos Double en lugar de double
    }

    public void registrarMes(Integer mes, Double ventas, Double gastos) {
        if (mes == null || mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes debe estar entre 1 y 12.");
        }
        if (ventas == null || ventas < 0) {
            throw new IllegalArgumentException("Las ventas deben ser un valor positivo.");
        }
        if (gastos == null || gastos < 0) {
            throw new IllegalArgumentException("Los gastos deben ser un valor positivo.");
        }
        datosMensuales[mes - 1][0] = ventas;
        datosMensuales[mes - 1][1] = gastos;
    }

    public Double totalVentas() {
        Double total = 0.0;
        for (int i = 0; i < 12; i++) {
            total += datosMensuales[i][0] != null ? datosMensuales[i][0] : 0.0;
        }
        return total;
    }

    public Double totalGastos() {
        Double total = 0.0;
        for (int i = 0; i < 12; i++) {
            total += datosMensuales[i][1] != null ? datosMensuales[i][1] : 0.0;
        }
        return total;
    }

    public Double promedioVentas() {
        return totalVentas() / 12;
    }

    public int mesMayorVenta() {
        Double maxVenta = datosMensuales[0][0];
        int mesMax = 1;
        for (int i = 1; i < 12; i++) {
            if (datosMensuales[i][0] != null && datosMensuales[i][0] > maxVenta) {
                maxVenta = datosMensuales[i][0];
                mesMax = i + 1;
            }
        }
        return mesMax;
    }

    public int mesMenorVenta() {
        Double minVenta = datosMensuales[0][0];
        int mesMin = 1;
        for (int i = 1; i < 12; i++) {
            if (datosMensuales[i][0] != null && datosMensuales[i][0] < minVenta) {
                minVenta = datosMensuales[i][0];
                mesMin = i + 1;
            }
        }
        return mesMin;
    }

    public int mesMayorGasto() {
        Double maxGasto = datosMensuales[0][1];
        int mesMax = 1;
        for (int i = 1; i < 12; i++) {
            if (datosMensuales[i][1] != null && datosMensuales[i][1] > maxGasto) {
                maxGasto = datosMensuales[i][1];
                mesMax = i + 1;
            }
        }
        return mesMax;
    }

    public Double[] obtenerDatosMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes debe estar entre 1 y 12.");
        }
        return datosMensuales[mes - 1];
    }

    // Método para obtener el nombre del mes
    public String obtenerNombreMes(int mes) {
        String[] nombresMeses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return nombresMeses[mes - 1]; // Ajustamos para que los meses empiecen en 1
    }

    public String generarInforme(int anio) {
        StringBuilder informe = new StringBuilder();
        informe.append("Informe del año: ").append(anio).append("\n");
        informe.append("-----------------------------------\n");
        informe.append("Mes\tVentas\tGastos\n");

        // Cambié el DecimalFormat para que no muestre notación científica
        DecimalFormat formato = new DecimalFormat("#,##0.00");  // Limitar a 2 decimales

        for (int i = 0; i < 12; i++) {
            informe.append(obtenerNombreMes(i + 1)).append("\t")
                   .append(formato.format(datosMensuales[i][0] != null ? datosMensuales[i][0] : 0.0)).append("\t")
                   .append(formato.format(datosMensuales[i][1] != null ? datosMensuales[i][1] : 0.0)).append("\n");
        }

        informe.append("\nTotal Ventas: ").append(formato.format(totalVentas())).append("\n");
        informe.append("Total Gastos: ").append(formato.format(totalGastos())).append("\n");
        informe.append("Promedio Ventas: ").append(formato.format(promedioVentas())).append("\n");

        int mesMayor = mesMayorVenta();
        int mesMenor = mesMenorVenta();
        informe.append("Mes de mayor venta: ").append(obtenerNombreMes(mesMayor)).append(", Ventas: ")
               .append(formato.format(datosMensuales[mesMayor - 1][0])).append(", Gastos: ")
               .append(formato.format(datosMensuales[mesMayor - 1][1])).append("\n");

        int mesMayorGasto = mesMayorGasto();
        informe.append("Mes de mayor gasto: ").append(obtenerNombreMes(mesMayorGasto)).append(", Ventas: ")
               .append(formato.format(datosMensuales[mesMayorGasto - 1][0])).append(", Gastos: ")
               .append(formato.format(datosMensuales[mesMayorGasto - 1][1])).append("\n");

        informe.append("Mes de menor venta: ").append(obtenerNombreMes(mesMenor)).append(", Ventas: ")
               .append(formato.format(datosMensuales[mesMenor - 1][0])).append(", Gastos: ")
               .append(formato.format(datosMensuales[mesMenor - 1][1])).append("\n");

        return informe.toString();
    }
}
