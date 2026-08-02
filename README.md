# MyBatis with Spring Boot — Playground / Showcase

A Spring Boot project used to explore and compare different ways of working with
**MyBatis**, rather than to ship a specific feature. It's built around the classic
`world` database schema (`country` / `city` / `countrylanguage`) as a realistic-but-simple
domain to experiment with.

## What this repo is meant to showcase

- **XML mappers vs. annotation-based (Java) mappers** — each entity has two parallel
  mapper implementations (e.g. `CityMapper` + `CityMapper.xml` vs. `CityJavaMapper`) so the
  two styles can be compared side by side for the same queries.
- **A shared `BaseMapper<T, ID>` contract** — a generic CRUD interface that the XML-backed
  mappers implement, showing how MyBatis mappers can still follow a common abstraction.
- **Cross-cutting concerns via AOP** — a `MapperLoggingAspect` wraps every `@Mapper`-annotated
  call to log method, arguments, and execution time, as an example of instrumenting MyBatis
  without touching the mappers themselves.
- **Result mapping techniques** — handling of composite keys, column-to-property mapping
  (including MyBatis's automatic underscore/camelCase and case-insensitive matching), and
  reusable `@ResultMap` references shared between XML and annotated mappers.
- **Different test strategies for MyBatis mappers** — `@MybatisTest` against an embedded
  database vs. against the "real" configured data source, illustrating the trade-offs
  between fast/isolated tests and tests closer to production configuration.

## Stack

- Spring Boot 4.1, Java 25
- MyBatis (Spring Boot starter) 4.0.1
- MySQL (runtime), H2 (test)
- Lombok, AspectJ

## Layout at a glance

```
src/main/java/org/kgromov/
├── controller/     # thin REST entry point
├── mappers/         # BaseMapper + XML-backed and annotation-based mapper pairs, logging aspect
├── model/            # domain entities
└── service/          # service layer

src/main/resources/mapper/   # XML mapper definitions
src/test/java/org/kgromov/   # embedded-DB vs. prod-DB MyBatis test base classes
```