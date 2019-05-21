package com.example.tobiassilva.calculadoradenotas;

class NotaExceptions{

        void notaException(double nota1, double nota2) throws Exception{

            if((nota1 > 10) ||(nota2 > 10)){

                throw new Exception("Nota Inválida");

            }
        }

    void notaException(String s1, String s2) throws Exception{

        if((s1.equals("")) ||(s2.equals(""))){

            throw new Exception("Nota Inválida");

        }
    }
}
