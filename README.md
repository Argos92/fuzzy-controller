# Алгоритмы нечеткого логического вывода

## Создание функции принадлежности.
+ **Треугольная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.triangular(0.25, 0.5, 0.75)
``` 
+ **Трацепевидная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.trapezius(0.2, 0.4, 0.6, 0.6)
``` 
+ **Z-образная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.z(0.25, 0.75)
```
+ **S-образная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.s(0.25, 0.75)
```
+ **Сигмоидальная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.sigmoid(0.25, 0.75)
```
+ **Линейная Z-образная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.zLine(0.25, 0.75)
```
+ **Линейная S-образная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.sLine(0.25, 0.75)
```
+ **Колокообразная функция принадлежности:**
```
MembershipFunction mf = MembershipFunctionBuilder.bell(0.2, 1, 0.50)
```

##Создание лингвистической переменной.
```
Variable service = VariableBuilder.of(
    "service", Map.of(
        "service_000", bell(0.2, 1, 0.00),
        "service_025", sLine(0.25, 0.75),
        "service_050", zLine(0.25, 0.75),
        "service_075", s(0.50, 1),
        "service_100", z(0.50, 1)));
```

##Нечекий алгоритм вывода Ларсена.
**Создание нечеткого правила.**
```
Larsen.Rule rule = LarsenBuilder.rule(and(food.is("food_000"), service.is("service_000")), tip.get("tip_000"))
```
**Создание системы нечеткого логического вывода**
```
Larsen controller = LarsenBuilder.of(List.of(rule1, rule2, rule3, rule4))
```
**Вычисление усеченной функции принадлежности**
```
MembershipFunction mf = controller.compose(Map.of("food", 0.25, "service", 0.25))
```

##Нечекий алгоритм вывода Мамдани.
**Создание нечеткого правила.**
```
Mamdani.Rule rule = MamdaniBuilder.rule(and(food.is("food_000"), service.is("service_000")), tip.get("tip_000"))
```
**Создание системы нечеткого логического вывода**
```
Mamdani controller = MamdaniBuilder.of(List.of(rule1, rule2, rule3, rule4))
```
**Вычисление усеченной функции принадлежности**
```
MembershipFunction mf = controller.compose(Map.of("food", 0.25, "service", 0.25))
```

##Нечекий алгоритм вывода Цукамото.
**Создание нечеткого правила.**
```
Tsukamoto.Rule rule = TsukamotoBuilder.rule(and(food.is("food_000"), service.is("service_000")), tip.get("tip_000"))
```
**Создание системы нечеткого логического вывода**
```
Tsukamoto controller = TsukamotoBuilder.of(List.of(rule1, rule2, rule3, rule4))
```
**Дефаззификация.**
```
double mf = controller.clarity(Map.of("food", 0.25, "service", 0.25))
```

##Нечекий алгоритм вывода Сугено.
**Создание нечеткого правила.**
```
Operator conclusion = new Operator() {
    @Override
    public double calc(Map<String, Double> parameters) {
        return parameters.get("tip_000") * 3;
    }

    @Override
    public List<Term> getTerms() {
        return Collections.emptyList();
    }
};

Sugeno.Rule rule = SugenoBuilder.rule(and(food.is("food_000"), service.is("service_000")), conclusion)
```
**Создание системы нечеткого логического вывода**
```
Sugeno controller = SugenoBuilder.of(List.of(rule1, rule2, rule3, rule4))
```
**Дефаззификация.**
```
double mf = controller.clarity(Map.of("food", 0.25, "service", 0.25))
```


##Упрощенный алгоритм нечеткого логического вывода.
**Создание нечеткого правила.**
```
SimplifiedAlgorithm.Rule rule = SimplifiedAlgorithmBuilder.rule(and(food.is("food_000"), service.is("service_000")), 0.00)
```
**Создание системы нечеткого логического вывода**
```
SimplifiedAlgorithm controller = SimplifiedAlgorithmBuilder.of(List.of(rule1, rule2, rule3, rule4))
```
**Дефаззификация.**
```
double mf = controller.clarity(Map.of("food", 0.25, "service", 0.25))
```

##Методы дефазификации
+ **Метод центра тяжести.**

По умолчанию на интервале [0, 1.0] с шагом 0.0001:
```
ClarityMethod cm = ClarityMethodBuilder.centerOfGravity() 
```
С заданием интервала и шага:
```
ClarityMethod cm = ClarityMethodBuilder.centerOfGravity(0.0, 100.0, 1.0)
```
+ **Метод среднего максимума.**

По умолчанию на интервале [0, 1.0] с шагом 0.0001:
```
ClarityMethod cm = ClarityMethodBuilder.averageMaximum()
```
С заданием интервала и шага:
```
ClarityMethod cm = ClarityMethodBuilder.centerOfGravity(0.0, 100.0, 1.0)
```
+ **Метод левого максимума.**

По умолчанию на интервале [0, 1.0] с шагом 0.0001:
```
ClarityMethod cm = ClarityMethodBuilder.leftMaximum()
```
С заданием интервала и шага:
```
ClarityMethod cm = ClarityMethodBuilder.centerOfGravity(0.0, 100.0, 1.0)
```
+ **Метод правого максимума.**

По умолчанию на интервале [0, 1.0] с шагом 0.0001:
```
ClarityMethod cm = ClarityMethodBuilder.rightMaximum()
```
С заданием интервала и шага:
```
ClarityMethod cm = ClarityMethodBuilder.rightMaximum(0.0, 100.0, 1.0)
```
