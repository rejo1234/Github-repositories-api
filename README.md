# GitHub Repositories API

Projekt API który pobiera dane o repozytoriach użytkowników z GitHub i udostępnia je w formie własnego API. API filtruje repozytoria,
zwracając tylko te, które nie są forkami; jeśli użytkownik nie istnieje, zwracany jest własny handler błędu. Projekt spełnia wszystkie wymagania zadania: minimalna liczba modeli,
architektura Controller/Service/Client, brak paginacji, bezpieczeństwa i cache, oraz testy integracyjne realizowane z Wiremock.

## Technologie
- Java 17+  
- Spring Boot 3.3.1  
- Spring Web (REST API)  
- Lombok  
- SpringDoc OpenAPI (UI dokumentacji API)  
- Wiremock + Spring Cloud Contract Wiremock (testy integracyjne)  
- Maven (build i zarządzanie zależnościami)  

## Funkcjonalności
- Pobieranie wszystkich repozytoriów użytkownika z GitHub
- Filtrowanie repozytoriów fork i non-fork
- Obsługa sytuacji, gdy użytkownik nie istnieje
- Testy integracyjne emulujące serwer GitHub

## Uruchomienie
Sklonuj repozytorium z GitHuba, używając linku:
https://github.com/rejo1234/Github-repositories-api.git

Wejdź do katalogu projektu (cd Github-repositories-api).

Uruchom aplikację poleceniem mvn spring-boot:run.

Po uruchomieniu API będzie dostępne pod adresem: http://localhost:8080.
