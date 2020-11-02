# Gamma Bot [![example workflow name](https://github.com/gammadev/gamma-bot/workflows/Verify%20CI/badge.svg)](https://google.com) [![Reliability rating](https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=alert_status) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=coverage)](https://sonarcloud.io/dashboard?id=https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=alert_status)

Gamma Bot is a Kotlin bot that involve a lot of features to improve development skills under different scenarios.

>Currently it is focused on Music feature, but for future implementations it would have some games and day-to-day facilitators

## Setup


## Code style

The code style is based on [Kotlin style guide](https://kotlinlang.org/docs/reference/code-style-migration-guide.html) and [Google's java style guide](https://google.github.io/styleguide/javaguide.html), with some changes.

One of them is the modifiers and members order, that were adapted to Kotlin.

For the modifiers:
```kotlin 
override public protected private internal const lateinit inline noinline infix abstract open final inner
```

For the members: 
```kotlin 
object val var fun interface enum companion
```

## External libraries

- [LavaPlayer - Audio player library for Discord](https://github.com/sedmelluq/lavaplayer) [1.5.33]
- [MockK - Mocking library for Kotlin](https://mockk.io/) [1.10.2]

## Licenses
