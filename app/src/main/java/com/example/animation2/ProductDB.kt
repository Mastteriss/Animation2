package com.example.animation2

class ProductDB {
    companion object {
        val products = mutableListOf(
            Product("Хлеб",
                "99,99 ₽",
                R.drawable.xleb),
            Product("Сахар",
                "160,99 ₽",
                R.drawable.milk),
            Product("Молоко",
                "290,99 ₽",
                R.drawable.med),
            Product("Сыр",
                "250,99 ₽",
                R.drawable.cheese),
            Product("Помидоры",
                "150,99 ₽",
                R.drawable.tomat),
        )
    }
}