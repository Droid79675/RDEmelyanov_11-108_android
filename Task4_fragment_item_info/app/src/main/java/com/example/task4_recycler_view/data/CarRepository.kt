package com.example.task4_recycler_view.data

import com.example.task4_recycler_view.model.Car

object CarRepository {
    val cars = arrayListOf<Car>(
        Car(
            0,
            "Nissan Skyline R34",
            1560,
            280,
            65,
            "https://www.super-hobby.ru/zdjecia/5/4/6/981_rd.jpg"
        ),
        Car(
            1,
            "Toyota Supra A80",
            1960,
            280,
            70,
            "https://a.d-cd.net/1c04acds-960.jpg"
        ),
        Car(
            2,
            "Ford Mustang V",
            2095,
            210,
            59,
            "https://cdn3.focus.bg/autodata/i/ford/mustang/mustang-v/large/e66b3163435cd2cae156fe00bef73854.jpg"
        ),
        Car(
            3,
            "Nissan Sylvia S15",
            1240,
            245,
            62,
            "https://5koleso.ru/wp-content/uploads/2021/10/bomb0846.jpg"
        ),
        Car(
            4,
            "Chevrolet Corvette C2 Stingray",
            1525,
            340,
            76,
            "https://carakoom.com/data/wall/787/6388a23a_medium.jpg"
        )
    )

}