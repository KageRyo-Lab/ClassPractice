class Car:
    def __init__(self, color):
        self.color=color
    def show(self):
        print(f'color={self.color}')
class Truck(Car):
    def __init__(self, doors, owner, color):
        super().__init__(color)
        self.color=color
        self.doors=doors
        self.owner=owner
    def show(self):
        print('doors=',self.doors,', owner=',self.owner,', color=',self.color)
    
Car('red').show()
Truck(2,'Tom','blue').show()