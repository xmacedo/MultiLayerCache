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

### Eviction Strategies
Layer | Eviction Type | Config
------------ | ------------- | ------------- 
Caffeine | LRU (Least Recently Used) / TTL | **maximumSize**, expireAfterWrite
Redis | Configurable | **maxmemory-policy** in redis.conf

### Performance Testing
> Test the latency with cold vs warm caches:

Test Case | Cache Hit     | Time(ms)
------------ |---------------| ------------- 
Cold Cache | Non (DB)      | 150
Second request | L1 (Caffeine) | 3
After 10 min | L2 (Redis     | 10-20
After 30 min| DB            | 150

### Smart Strategy
- On update, evict the cache
- On read, repopulate if missing


> Spring handles both L1 and L2 cache eviction if you use the same key + CompositeCacheManager.

## Conclusion: When to Use Multi-Layer Caching

### Best For:
 - High-read, performance-critical systems
- Microservices or distributed environments
- Apps needing fallback and resilience
- Shared data across nodes
### Avoid If:
- Simple/local-only apps
- Very dynamic, frequently changing data
- You can’t afford the complexity

## FAQ: Common Caching Questions
### What is L1 and L2 cache in Spring Boot?
- L1 (Level 1): Local cache (Caffeine)
- L2 (Level 2): Distributed cache (Redis)

### What is the difference between @Cacheable and @CachePut?

Annotation | Behavior 
------------ | ------------- 
@Cacheable | Skips methods if jey exists in cache
@CachePut | Always runs method and updates the cache


### Is Redis a Second-Level Cache?
Yes — Redis acts as L2 in Spring Boot when paired with L1 (e.g., Caffeine). It is shared and accessible across instances.

### How is Caching done in Spring Boot?
1. Enable @EnableCaching
2. Use @Cacheable, @CachePut, @CacheEvict
3. Configure a cache manager
4. Plug in providers: Caffeine, Redis, etc.


Multi-layer caching brings together the **speed of local access** and the **power of shared memory**. With Spring Boot, Caffeine, and Redis, you get the best of both worlds.