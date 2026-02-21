# Multi-Layer caching in Spring Boot

In performance-sensitive applications, especially microservices and high-throughput APIs, minimizing redundant data access is crucial. Caching addresses this by storing frequently used data closer to the application.

But when your system scales across instances, regions, or teams, a single-layer cache may not cut it. That’s where multi-layer caching becomes a game-changer.

## What is Multi-Layer Caching?

### Definition
Multi-layer caching involves multiple caching levels, typically:
- L1 Cache — Fastest, in-memory (e.g., Caffeine), JVM-local
- L2 Cache — Shared, distributed (e.g., Redis), accessible across instances
- L3 (optional) — Persistent storage (e.g., DB or API)

### Strategy
Let’s build a Spring Boot project with:

1. Caffeine (L1 local cache)
2. Redis (L2 distributed cache)

Cache configuration [CacheConfig.java](src/main/java/br/com/xmacedo/multlayercache/config/CacheConfig.java)

### TTL (Time-To-Live) & Eviction Strategies
Set different TTLs for L1 and L2 to optimize freshness vs performance:

- L1 (Caffeine): Short TTL — 10 minutes
- L2 (Redis): Longer TTL — 30 minutes