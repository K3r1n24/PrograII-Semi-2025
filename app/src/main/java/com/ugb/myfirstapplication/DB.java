package com.ugb.myfirstapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Nirek";
    private static final int DATABASE_VERSION = 1;
    private static final String SQLdb = "CREATE TABLE productos (idProducto INTEGER PRIMARY KEY AUTOINCREMENT, codigo TEXT, nombre TEXT, presentacion TEXT, marca TEXT, precio REAL, urlFoto TEXT)";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica para actualizar la estructura de la base de datos si es necesario
    }

    public String administrarProductos(String accion, String[] datos) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String mensaje = "ok";
            String sql;
            SQLiteStatement stmt;

            switch (accion) {
                case "agregar":
                    sql = "INSERT INTO productos (codigo, nombre, presentacion, marca, precio, urlFoto) VALUES (?, ?, ?, ?, ?, ?)";
                    stmt = db.compileStatement(sql);
                    bindParameters(stmt, datos, false);
                    stmt.executeInsert();
                    break;

                case "modificar":
                    sql = "UPDATE productos SET codigo = ?, nombre = ?, presentacion = ?, marca = ?, precio = ?, urlFoto = ? WHERE idProducto = ?";
                    stmt = db.compileStatement(sql);
                    bindParameters(stmt, datos, true);
                    stmt.executeUpdateDelete();
                    break;

                case "eliminar":
                    sql = "DELETE FROM productos WHERE idProducto = ?";
                    stmt = db.compileStatement(sql);
                    stmt.bindLong(1, Long.parseLong(datos[0]));
                    stmt.executeUpdateDelete();
                    break;

                default:
                    mensaje = "Acción no válida";
            }

            db.close();
            return mensaje;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private void bindParameters(SQLiteStatement stmt, String[] datos, boolean includeId) {
        int startIndex = includeId ? 1 : 0;
        for (int i = startIndex; i < datos.length; i++) {
            if (i == 5) {
                stmt.bindDouble(i, Double.parseDouble(datos[i]));
            } else {
                stmt.bindString(i, datos[i]);
            }
        }
        if (includeId) {
            stmt.bindLong(7, Long.parseLong(datos[0]));
        }
    }

    public Cursor listaProductos() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM productos", null);
    }
}
