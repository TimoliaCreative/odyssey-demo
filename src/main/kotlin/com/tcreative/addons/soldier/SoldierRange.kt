package com.tcreative.addons.soldier

import com.tcreative.devtools.tranclate.builder.getResource
import com.tcreative.devtools.tranclate.systemaddon.SystemAddon

fun soldierRange(systemAddon: SystemAddon) {
    systemAddon.entity {
        name("soldier_range", "Soldier")
        loadTextures(this)
        resource {
            animations(getResource("entity/animations/soldier_npc.animation.json"))
            geometry(getResource("entity/geometries/soldier_npc.geo.json"))
            components {
                scripts {
                    preAnim(arrayListOf("variable.tcos0 = (Math.cos(query.modified_distance_moved * 38.17) * query.modified_move_speed / variable.gliding_speed_value) * 57.3;"))
                }
            }
            animControllers {
                sharedResAnimControllers(this)
            }
        }

        behaviour {


            componentGroups { loadTextureCompGroups(this) }
            components {
                sharedComponents(this)
                typeFamily(arrayListOf("mob", "addon"))
                behRangedAttack {
                    priority = 1
                }
                shooter {
                    type = "arrow"
                }
                attack {
                    damage = 4
                }
                equipment {
                    table("soldier_range") {
                        pool(rolls = 1) {
                            entry(type = "item", name = "minecraft:bow", weight = 1) { }
                        }
                    }
                }
                loot {
                    genTable("soldier") {
                        pool {
                            entry(type = "item", name = "arrow", weight = 10) {
                                functionSetCount(4)
                            }
                            entry(type = "item", name = "bone", weight = 10) {
                                functionSetCount(2)
                            }
                        }
                    }
                }
            }
            events { spawnEvent(this) }
        }
    }
}