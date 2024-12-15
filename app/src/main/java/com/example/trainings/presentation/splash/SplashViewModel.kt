package com.example.trainings.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.trainings.data.exercise.ExerciseDatabase
import com.example.trainings.domain.models.Exercise
import com.example.trainings.domain.models.Instruction
import com.example.trainings.domain.models.MuscleGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    val exerciseDatabase: ExerciseDatabase,
) : ViewModel() {

    suspend fun checkExercises() {
        withContext(Dispatchers.IO) {
            if (exerciseDatabase.exerciseDao().getCount() == 0)
                createDays().forEach { exerciseDatabase.exerciseDao().insert(it) }
        }
    }


    fun createDays() : List<Exercise> {
        return listOf(
            Exercise(
                title = "Жим лежа",
                description = "Комплексное упражнение, которое нацелено на грудные мышцы, плечи и трицепсы.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на скамью, ноги на полу."),
                    Instruction(
                        "Исполнение",
                        "Опустите штангу к груди и поднимите её обратно."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.CHEST),
                gifUrl = "https://avatars.dzeninfra.ru/get-zen_doc/1917356/pub_5e086cf9d5bbc300b102943e_5e086ffbddfef600ae71ee6c/orig"
            ),
            Exercise(
                title = "Становая тяга",
                description = "Упражнение для всего тела, которое в первую очередь нацелено на спину и ноги.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Встаньте с ногами на ширине бедер и возьмитесь за штангу."
                    ),
                    Instruction("Исполнение", "Поднимите штангу, выпрямляя бедра и колени.")
                ),
                muscleGroup = listOf(MuscleGroup.BACK, MuscleGroup.LEGS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/12/Rumynskaya_tyga_shtanga.gif"
            ),
            Exercise(
                title = "Приседания",
                description = "Упражнение для нижней части тела, которое нацелено на квадрицепсы, задние мышцы бедра и ягодицы.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте с ногами на ширине плеч."),
                    Instruction("Исполнение", "Опустите тело, сгибая колени и бедра.")
                ),
                muscleGroup = listOf(MuscleGroup.LEGS, MuscleGroup.GLUTES),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/03/Prised_so_shtangoj.gif"
            ),
            Exercise(
                title = "Жим плечами",
                description = "Упражнение, которое нацелено на плечи и трицепсы.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Сядьте или встаньте с гантелями в каждой руке на уровне плеч."
                    ),
                    Instruction(
                        "Исполнение",
                        "Поднимите веса над головой до полного выпрямления рук."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.SHOULDERS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/02/Zhim_shtangi_na_plechi.gif"
            ),
            Exercise(
                title = "Подтягивания",
                description = "Упражнение для верхней части тела, которое нацелено на спину и бицепсы.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Повисните на перекладине, ладони смотрят от вас."
                    ),
                    Instruction(
                        "Исполнение",
                        "Подтяните тело вверх, пока подбородок не окажется выше перекладины."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.BACK, MuscleGroup.ARMS),
                gifUrl = "https://opis-cdn.tinkoffjournal.ru/mercury/how-to-pull-up-03a.kf9garoee6xo..gif"
            ),
            Exercise(
                title = "Выпады",
                description = "Упражнение для нижней части тела, которое нацелено на квадрицепсы, задние мышцы бедра и ягодицы.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте прямо с ногами вместе."),
                    Instruction(
                        "Исполнение",
                        "Шагните вперед одной ногой и опустите бедра, пока оба колена не согнутся под углом 90 градусов."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS, MuscleGroup.GLUTES),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/09/vypady-vpered.gif"

            ),
            Exercise(
                title = "Сгибание рук на бицепс",
                description = "Изолирующее упражнение, нацеленное на бицепсы.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Встаньте с гантелями в каждой руке по бокам."
                    ),
                    Instruction("Исполнение", "Согните веса к плечам.")
                ),
                muscleGroup = listOf(MuscleGroup.ARMS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/03/Sgibanie_ruk_2.gif"
            ),
            Exercise(
                title = "Отжимания на брусьях",
                description = "Упражнение, которое нацелено на трицепсы.",
                instructions = listOf(
                    Instruction("Подготовка", "Сядьте на край скамьи с руками по бокам."),
                    Instruction(
                        "Исполнение",
                        "Опустите тело, сгибая локти, а затем поднимитесь обратно."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ARMS),
                gifUrl = "https://yourfitnesslife.ru/wp-content/uploads/2020/07/%D0%BE%D1%82%D0%B6%D0%B8%D0%BC%D0%B0%D0%BD%D0%B8%D1%8F-%D0%BD%D0%B0-%D0%B1%D1%80%D1%83%D1%81%D1%8C%D1%8F%D1%85-%D0%B4%D0%BB%D1%8F-%D0%B3%D1%80%D1%83%D0%B4%D0%B8.gif"
            ),
            Exercise(
                title = "Балансборд",
                description = "Упражнение для координации и баланса.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте на одну ногу."),
                    Instruction(
                        "Исполнение",
                        "Переносите вес с одной ноги на другую."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS),
                gifUrl = "https://static.tildacdn.one/tild3035-3530-4536-b766-353064333734/giphy-5.gif"
            ),
            Exercise(
                title = "Русские скручивания",
                description = "Упражнение для косых мышц живота.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Сядьте на пол, согнув колени и откинувшись немного назад."
                    ),
                    Instruction(
                        "Исполнение",
                        "Поворачивайте корпус влево и вправо, касаясь пола."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/02/Russkij_twist.gif"
            ),
            Exercise(
                title = "Пуловер с гантелей",
                description = "Упражнение для грудных и спинных мышц.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Лягте на скамью, держа гантель обеими руками над головой."
                    ),
                    Instruction(
                        "Исполнение",
                        "Опустите гантель за голову, затем верните в исходное положение."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.CHEST, MuscleGroup.BACK),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/05/Pullover_dve_ganteli.gif"
            ),
            Exercise(
                title = "Супермен",
                description = "Упражнение для укрепления спины.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на живот, вытянув руки и ноги."),
                    Instruction(
                        "Исполнение",
                        "Поднимите одновременно руки и ноги, удерживая тело в напряжении."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.BACK),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/06/Superman_otvedenie_ruk.gif"
            ),
            Exercise(
                title = "Боковая планка",
                description = "Упражнение для косых мышц живота.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на бок, опираясь на одно предплечье."),
                    Instruction(
                        "Исполнение",
                        "Поднимите тело, удерживая его в прямой линии."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/09/Bokovaya_planka_skruchivanie_loktem.gif"
            ),
            Exercise(
                title = "Тяга к поясу в наклоне",
                description = "Упражнение для спины.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Встаньте с гантелями в каждой руке, наклонившись вперед."
                    ),
                    Instruction("Исполнение", "Подтяните гантели к поясу.")
                ),
                muscleGroup = listOf(MuscleGroup.BACK),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/04/Tyaga_shtangi_v_naklone.gif"
            ),
            Exercise(
                title = "Сгибание ног на фитболе",
                description = "Упражнение для задней поверхности бедра.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на спину, положив ноги на фитбол."),
                    Instruction("Исполнение", "Согните ноги, подтягивая фитбол к себе.")
                ),
                muscleGroup = listOf(MuscleGroup.LEGS),
                gifUrl = "https://gymbeam.cz/blog/wp-content/uploads/2021/07/Fitlopta-Glute-Bridge.gif"
            ),
            Exercise(
                title = "Прыжки на месте",
                description = "Кардиоупражнение для повышения выносливости.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте прямо с ногами на ширине плеч."),
                    Instruction(
                        "Исполнение",
                        "Прыжками поднимайтесь вверх, приземляясь на носки."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/06/Tuck_jump.gif"
            ),
            Exercise(
                title = "Кроссфит-упражнение 'Табата'",
                description = "Интервальное кардиоупражнение.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Выберите 4 упражнения и выполняйте их по 20 секунд с 10-секундным отдыхом."
                    ),
                    Instruction("Исполнение", "Повторите цикл 8 раз.")
                ),
                muscleGroup = listOf(MuscleGroup.ABS, MuscleGroup.LEGS, MuscleGroup.ARMS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/08/tabata-dlja-novichkov.gif"
            ),
            Exercise(
                title = "Скручивания на фитболе",
                description = "Упражнение для пресса.",
                instructions = listOf(
                    Instruction("Подготовка", "Сядьте на фитбол, ноги на полу."),
                    Instruction(
                        "Исполнение",
                        "Скручивайте корпус, опускаясь назад и поднимаясь."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://gymbeam.cz/blog/wp-content/uploads/2021/07/Fitlopta-Crunches.gif"
            ),
            Exercise(
                title = "Махи гантелями",
                description = "Упражнение для плеч и верхней части спины.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Встаньте с гантелями в каждой руке по бокам."
                    ),
                    Instruction("Исполнение", "Поднимите гантели вперед до уровня плеч.")
                ),
                muscleGroup = listOf(MuscleGroup.SHOULDERS),
                gifUrl = "https://makefitness.pro/wp-content/uploads/2020/02/%D0%BC%D0%B0%D1%85%D0%B8-%D1%81-%D0%B3%D0%B0%D0%BD%D1%82%D0%B5%D0%BB%D1%8F%D0%BC%D0%B8-%D1%81%D1%82%D0%BE%D1%8F.gif"
            ),
            Exercise(
                title = "Прыжки через скакалку",
                description = "Кардиоупражнение для улучшения выносливости.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Держите скакалку в руках, ноги на ширине плеч."
                    ),
                    Instruction("Исполнение", "Прыгайте через скакалку, удерживая ритм.")
                ),
                muscleGroup = listOf(MuscleGroup.LEGS, MuscleGroup.ABS),
                gifUrl = "https://gripboard.ru/upload/img/kak-nauchitsya-prygat-na-skorostnoj-skakalke%20(1).gif"
            ),
            Exercise(
                title = "Тяга к груди",
                description = "Упражнение для спины и бицепсов.",
                instructions = listOf(
                    Instruction("Подготовка", "Сядьте на тренажер для тяги к груди."),
                    Instruction("Исполнение", "Тяните ручку к груди, сводя лопатки.")
                ),
                muscleGroup = listOf(MuscleGroup.BACK, MuscleGroup.ARMS),
                gifUrl = "https://instructorpro.ru/wp-content/uploads/2023/02/%D0%92%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D1%82%D1%8F%D0%B3%D0%B0-%D0%92%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5.gif"
            ),
            Exercise(
                title = "Плиометрические отжимания",
                description = "Упражнение для грудных мышц и трицепсов с элементами взрывной силы.",
                instructions = listOf(
                    Instruction("Подготовка", "Примите положение для отжиманий."),
                    Instruction(
                        "Исполнение",
                        "Отжимайтесь и в верхней точке оттолкнитесь, чтобы руки оторвались от пола."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS),
                gifUrl = "https://f.mixsport.pro/photo/19301/KBOFz.gif"
            ),
            Exercise(
                title = "Кранчи с поворотом",
                description = "Упражнение для косых мышц живота.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на спину с согнутыми коленями."),
                    Instruction(
                        "Исполнение",
                        "Поднимите верхнюю часть тела и поворачивайте в стороны."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/01/Velosiped_new.gif"
            ),
            Exercise(
                title = "Тяга гантели одной рукой",
                description = "Упражнение для спины.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Встаньте, опираясь одной рукой на скамью, другой держите гантель."
                    ),
                    Instruction("Исполнение", "Подтяните гантель к поясу.")
                ),
                muscleGroup = listOf(MuscleGroup.BACK),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/05/Tyaga_gantelej_opora_rukoj_na_skamu_2.gif"
            ),
            Exercise(
                title = "Махи ногами в стороны",
                description = "Упражнение для ягодиц и бедер.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте прямо, ноги на ширине плеч."),
                    Instruction(
                        "Исполнение",
                        "Поднимите одну ногу в сторону, удерживая тело в равновесии."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.GLUTES, MuscleGroup.LEGS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/07/Mah_nogoj_vbok_so_stulom-1.gif"
            ),
            Exercise(
                title = "Прыжки с поворотом",
                description = "Упражнение для кардио и координации.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте прямо, ноги на ширине плеч."),
                    Instruction(
                        "Исполнение",
                        "Прыгайте и поворачивайтесь на 180 градусов в воздухе."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS, MuscleGroup.ABS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/05/Pryzhki_s_povorotom.gif"
            ),
            Exercise(
                title = "Тяга штанги к подбородку",
                description = "Упражнение для плеч и спины.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте с штангой на уровне бедер."),
                    Instruction("Исполнение", "Подтяните штангу к подбородку, сводя локти.")
                ),
                muscleGroup = listOf(MuscleGroup.SHOULDERS, MuscleGroup.BACK),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/02/Tyaga_shtangi_k_podborodku.gif"
            ),
            Exercise(
                title = "Разгибание рук на трицепс",
                description = "Упражнение для трицепсов.",
                instructions = listOf(
                    Instruction(
                        "Подготовка",
                        "Сядьте на скамью, держа гантель обеими руками над головой."
                    ),
                    Instruction(
                        "Исполнение",
                        "Опустите гантель за голову, затем верните в исходное положение."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ARMS),
                gifUrl = "https://archive.prosto.academy/uploads/images/gallery/2023-07/u2tzLBdX1P3c7oPu-1-1-3.gif"
            ),
            Exercise(
                title = "Прыжки на одной ноге",
                description = "Упражнение для баланса и силы ног.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте на одну ногу."),
                    Instruction(
                        "Исполнение",
                        "Прыгайте на одной ноге, стараясь удерживать равновесие."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2020/06/Pryzhok_ladoni_koleno.gif"
            ),
            Exercise(
                title = "Круговые движения руками",
                description = "Упражнение для плеч и разминки.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте прямо, руки вытянуты в стороны."),
                    Instruction(
                        "Исполнение",
                        "Делайте круговые движения руками вперед и назад."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.SHOULDERS),
                gifUrl = "https://avatars.dzeninfra.ru/get-zen_doc/1861837/pub_5e20a602e6e8ef00b2d86de8_5e20a6c7118d7f00bcdbd72e/orig"
            ),
            Exercise(
                title = "Скручивания с гантелей",
                description = "Упражнение для пресса с дополнительным весом.",
                instructions = listOf(
                    Instruction("Подготовка", "Лягте на спину, держа гантель на груди."),
                    Instruction(
                        "Исполнение",
                        "Скручивайте корпус, поднимая верхнюю часть тела."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2021/05/Skruchivanie_s_gantelu.gif"
            ),
            Exercise(
                title = "Прыжки на платформе",
                description = "Упражнение для ног и кардио.",
                instructions = listOf(
                    Instruction("Подготовка", "Встаньте перед платформой."),
                    Instruction(
                        "Исполнение",
                        "Прыгайте на платформу, приземляясь на обе ноги."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.LEGS),
                gifUrl = "https://goodlooker.ru/wp-content/uploads/2017/07/%D0%9F%D1%80%D1%8B%D0%B6%D0%BE%D0%BA-%D0%BD%D0%B0-%D0%BF%D0%BB%D0%B0%D1%82%D1%84%D0%BE%D1%80%D0%BC%D1%83.gif"
            ),
            Exercise(
                title = "Качание пресса на фитболе",
                description = "Упражнение для пресса с использованием фитбола.",
                instructions = listOf(
                    Instruction("Подготовка", "Сядьте на фитбол, ноги на полу."),
                    Instruction(
                        "Исполнение",
                        "Наклоняйтесь назад, затем возвращайтесь в исходное положение."
                    )
                ),
                muscleGroup = listOf(MuscleGroup.ABS),
                gifUrl = "https://gymbeam.cz/blog/wp-content/uploads/2022/06/Oblique-Crunch.gif"
            )
        )
    }
}