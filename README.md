# Elevator-simulator
Symulator wind z prostym GUI.

<p align="center">
  <img src="demo/demo.gif" alt="animated" />
</p>

## Opis
Aplikacja pozwala na stworzenie do 16 wind. Używając Combo Boxes użytkownik podejmuje decyzje na jakie piętro chcę wezwać windę. Po wybraniu wind o najmniejszym obciążeniu wzywana jest ta, która znajduję się najbliżej. Każda z wind przechowuje w ArrayDeque piętra, które musi odwiedzić, podczas dodawania nowego celu są one sortowane. Piętra są dzielone na dwie grupy, wyższe i niższe od aktualnej pozycji windy, następnie są sortowane i łaczone. Ruch windy jest wykonywany co jedną sekundę. 
W aplikacji wykorzystuje wzorzec MVC oraz Stan (State).

## Język 
* Java 17.0.4

## Uruchomienie
```
javac Main.java
```
```
java Main
```
