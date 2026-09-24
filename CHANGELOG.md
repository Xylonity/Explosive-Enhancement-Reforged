# 1.2.0
- Particles can now be spawned properly via ingame commands, specifying a certain size for some values, for example: /particle explosiveenhancement:fireball{scale:4} ~ ~ ~
- Fixed a crash with explosions of radius 0, like the ones from Iron's Spells 'n Spellbooks or Goety
- Explosions with a radius of 0 now keep their vanilla particles
- Only the vanilla explosion particles are replaced now, instead of cancelling the rest of the explosion, which stopped blocks from being destroyed and fire from spreading when other mods (like TaCZ) finalized their own explosions