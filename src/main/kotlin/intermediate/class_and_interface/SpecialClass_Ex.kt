package intermediate.class_and_interface

// 여기서도 상속 가능(같은 패키지 내부니까)
class HumanExample(val humanName: String): Mammal(humanName)