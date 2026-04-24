# Java Concurrency Examples

This folder contains small Java examples showing the difference between `volatile`, `synchronized`, and `ReentrantLock`.

## Files

- `VolatileExample.java`
  Shows a `volatile` flag used for visibility between threads.

- `VolatileCounterWrong.java`
  Shows why `volatile` does not make `count++` thread-safe.

- `SynchronizedExample.java`
  Shows a synchronized method protecting shared state.

- `SynchronizedBlockExample.java`
  Shows a synchronized block using a dedicated lock object.

- `ReentrantLockExample.java`
  Shows explicit locking with `ReentrantLock`.

- `TryLockExample.java`
  Shows how `tryLock()` can avoid blocking when a lock is unavailable.

## When to use what

### `volatile`

Use `volatile` when:

- One thread updates a value
- Other threads only need to read the latest value
- You do not need multiple operations to happen atomically

Good for:

- Stop flags
- Status flags
- Simple shared configuration values

Not enough for:

- Counters with `count++`
- Check-then-act logic
- Any multi-step shared-state update

## `synchronized`

Use `synchronized` when:

- Multiple threads access shared mutable state
- You need both visibility and mutual exclusion
- You want the simplest built-in locking approach

Good for:

- Protecting counters
- Guarding shared collections
- Small critical sections

## `ReentrantLock`

Use `ReentrantLock` when:

- You need more control than `synchronized`
- You want `tryLock()`
- You want timed lock attempts
- You want interruptible locking
- You want optional fairness behavior

Good for:

- More advanced concurrency control
- Situations where blocking forever is undesirable
- Complex locking workflows

## Quick summary

- `volatile` = visibility only
- `synchronized` = visibility + mutual exclusion
- `ReentrantLock` = visibility + mutual exclusion + advanced lock features

## Compile

Run this inside the folder:

```bash
javac *.java
```

## Run examples

```bash
java VolatileExample
java VolatileCounterWrong
java SynchronizedExample
java SynchronizedBlockExample
java ReentrantLockExample
java TryLockExample
```

## Expected behavior

- `VolatileExample`:
  The worker thread stops after the main thread changes the shared flag.

- `VolatileCounterWrong`:
  The final count may be less than `20000` because `count++` is not atomic.

- `SynchronizedExample` and `SynchronizedBlockExample`:
  The final count should be correct.

- `ReentrantLockExample`:
  The final count should be correct.

- `TryLockExample`:
  One thread may acquire the lock while another may fail immediately.
