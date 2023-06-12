# Gamma Bot [![Build](https://github.com/gammadev/gamma-bot/actions/workflows/build.yml/badge.svg)](https://github.com/gammadev/gamma-bot/actions?query=Build+and+Quality) [![Reliability rating](https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=reliability_rating)](https://sonarcloud.io/component_measures?id=gammadev_gamma-bot&metric=Reliability) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=gammadev_gamma-bot&metric=coverage)](https://sonarcloud.io/component_measures?id=gammadev_gamma-bot&metric=Coverage)

Gamma Bot is a Kotlin bot that involves a lot of features to improve my own development skills under different scenarios.

>Currently, it is focused on Music feature, but for future implementations it would have some games and day-to-day facilitators

## Setup


## Code style

The code style is based on [Kotlin style guide](https://kotlinlang.org/docs/reference/code-style-migration-guide.html) and [Google's java style guide](https://google.github.io/styleguide/javaguide.html), with some changes.

One of them is the modifiers and members order, that were adapted to Kotlin.

For the modifiers:
```kotlin 
override public protected private internal const lateinit inline infix abstract open final
```

For the members: 
```kotlin 
object val var fun interface enum companion
```

## External libraries

- [JDA (Java Discord API)](https://github.com/DV8FromTheWorld/JDA) [4.2.0_204]
- [LavaPlayer - Audio player library for Discord](https://github.com/sedmelluq/lavaplayer) [1.3.78]
- [MockK - Mocking library for Kotlin](https://mockk.io/) [1.10.2]
