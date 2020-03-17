# Bank
Aplikacja jest podzielona na "czesc kliencka" oddzialu banku pozwalajaca tworzyc nowe konta oraz definiowac operacje bankowe oraz czesc "centrali banku".
Dane z aplikacji klienckiej sa zapisywane do plikow.
Aplikacja centrali pozwala na wczytanie zawartosci plikow i na ich podstawie tworzone sa obiekty w tym obiekt klasy "bank".
Operacje bankowe sa przetwarzanew za pomoca puli watkow, mozna zrownoleglic operacje przetwarzania.
Pliki sa przetwarzane za pomoca kolejki plikow i przenoszone do folderu "processed".
Na koniec pracy instancja/obiekt klasy bank zapisuje stan kont w plikach w folderze "state".