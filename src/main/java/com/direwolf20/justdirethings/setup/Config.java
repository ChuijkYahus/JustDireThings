package com.direwolf20.justdirethings.setup;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();

    public static final String OVERLAY_POSITION = "overlay_position";
    public static ModConfigSpec.IntValue OVERLAY_X;
    public static ModConfigSpec.IntValue OVERLAY_Y;

    public static final String CATEGORY_GENERAL = "general";
    public static ModConfigSpec.IntValue MINIMUM_MACHINE_TICK_SPEED;

    public static final String CATEGORY_GENERATOR_T1 = "generator_t1";
    public static ModConfigSpec.IntValue GENERATOR_T1_FE_PER_FUEL_TICK;
    public static ModConfigSpec.IntValue GENERATOR_T1_BURN_SPEED_MULTIPLIER;
    public static ModConfigSpec.IntValue GENERATOR_T1_MAX_FE;
    public static ModConfigSpec.IntValue GENERATOR_T1_FE_PER_TICK;

    public static final String CATEGORY_FUEL_CANISTER = "fuel_canister";
    public static ModConfigSpec.IntValue FUEL_CANISTER_MINIMUM_TICKS_CONSUMED;
    public static ModConfigSpec.IntValue FUEL_CANISTER_MAXIMUM_FUEL;

    public static final String ENERGY_TRANSMITTER_T1 = "energy_transmitter_t1";
    public static ModConfigSpec.DoubleValue ENERGY_TRANSMITTER_T1_LOSS_PER_BLOCK;
    public static ModConfigSpec.IntValue ENERGY_TRANSMITTER_T1_MAX_RF;
    public static ModConfigSpec.IntValue ENERGY_TRANSMITTER_T1_RF_PER_TICK;

    public static final String CATEGORY_POCKET_GENERATOR = "pocket_generator";
    public static ModConfigSpec.IntValue POCKET_GENERATOR_FE_PER_FUEL_TICK;
    public static ModConfigSpec.IntValue POCKET_GENERATOR_BURN_SPEED_MULTIPLIER;
    public static ModConfigSpec.IntValue POCKET_GENERATOR_MAX_FE;
    public static ModConfigSpec.IntValue POCKET_GENERATOR_FE_PER_TICK;

    public static void register(ModContainer container) {
        //registerServerConfigs(container);
        registerCommonConfigs(container);
        registerClientConfigs(container);
    }

    private static void registerClientConfigs(ModContainer container) {
        overlayConfig();
        container.registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }

    private static void registerCommonConfigs(ModContainer container) {
        generalConfig();
        generatorT1Config();
        energyTransmitter();
        fuelCanisterConfig();
        pocketGeneratorConfig();
        container.registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }

    private static void registerServerConfigs(ModContainer container) {

        container.registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    private static void overlayConfig() {
        CLIENT_BUILDER.comment("Cooldown Overlay Position").push(OVERLAY_POSITION);
        OVERLAY_X = CLIENT_BUILDER.comment("The X position of the cooldown overlay - this is pixels left from the center of the screen")
                .defineInRange("overlay_x_position", 91, -500, 500);
        OVERLAY_Y = CLIENT_BUILDER.comment("The Y position of the cooldown overlay - this is pixels up from the bottom of the screen")
                .defineInRange("overlay_y_position", 70, 0, 500);
        CLIENT_BUILDER.pop();
    }

    private static void generalConfig() {
        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        MINIMUM_MACHINE_TICK_SPEED = COMMON_BUILDER.comment("The minimum tick speed machines can be set to. Defaults to 1, meaning every tick")
                .defineInRange("minimum_machine_tick_speed", 1, 1, 100);
        COMMON_BUILDER.pop();
    }

    private static void generatorT1Config() {
        COMMON_BUILDER.comment("Generator T1").push(CATEGORY_GENERATOR_T1);
        GENERATOR_T1_FE_PER_FUEL_TICK = COMMON_BUILDER.comment("The amount of Forge Energy created per burn tick of fuel. Coal has 1600 burn ticks. Sticks have 100 burn ticks.")
                .defineInRange("generator_t1_fe_per_fuel_tick", 15, 1, Integer.MAX_VALUE);
        GENERATOR_T1_BURN_SPEED_MULTIPLIER = COMMON_BUILDER.comment("The multiplier for the burn speed, making the generator run faster. Coal is 1600 ticks to burn, if you set this to 10, it will burn in 160 ticks")
                .defineInRange("generator_t1_burn_speed_multiplier", 4, 1, 1000);
        GENERATOR_T1_MAX_FE = COMMON_BUILDER.comment("The maximum amount of Forge Energy the generator can hold in its buffer")
                .defineInRange("generator_t1_max_fe", 1000000, 1, Integer.MAX_VALUE);
        GENERATOR_T1_FE_PER_TICK = COMMON_BUILDER.comment("The FE per Tick that the generator outputs")
                .defineInRange("generator_t1_fe_per_tick", 1000, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    private static void fuelCanisterConfig() {
        COMMON_BUILDER.comment("Fuel Canister").push(CATEGORY_FUEL_CANISTER);
        FUEL_CANISTER_MINIMUM_TICKS_CONSUMED = COMMON_BUILDER.comment("The amount of ticks 'consumed' per operation in the furnace. Lower is more efficient fuel use.")
                .defineInRange("fuel_canister_minimum_ticks_consumed", 200, 100, Integer.MAX_VALUE);
        FUEL_CANISTER_MAXIMUM_FUEL = COMMON_BUILDER.comment("The maximum amount of fuel (in ticks) permitted in the fuel canister.")
                .defineInRange("fuel_canister_maximum_fuel", 10000000, 100, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    private static void energyTransmitter() {
        COMMON_BUILDER.comment("Energy Transmitter T1").push(ENERGY_TRANSMITTER_T1);
        ENERGY_TRANSMITTER_T1_MAX_RF = COMMON_BUILDER.comment("The maximum energy storage")
                .defineInRange("energy_transmitter_t1_max_rf", 1000000, 1, Integer.MAX_VALUE);
        ENERGY_TRANSMITTER_T1_RF_PER_TICK = COMMON_BUILDER.comment("The maximum RF transmitted per tick to machines and other transmitters")
                .defineInRange("energy_transmitter_t1_rf_per_tick", 1000, 1, Integer.MAX_VALUE);
        ENERGY_TRANSMITTER_T1_LOSS_PER_BLOCK = COMMON_BUILDER.comment("The energy loss per block distance in percent")
                .defineInRange("energy_transmitter_t1_loss_per_block", 1.0, 0, 100);
        COMMON_BUILDER.pop();
    }

    private static void pocketGeneratorConfig() {
        COMMON_BUILDER.comment("Pocket Generator").push(CATEGORY_POCKET_GENERATOR);
        POCKET_GENERATOR_FE_PER_FUEL_TICK = COMMON_BUILDER.comment("The amount of Forge Energy created per burn tick of fuel. Coal has 1600 burn ticks. Sticks have 100 burn ticks.")
                .defineInRange("pocket_gen_fe_per_fuel_tick", 15, 1, Integer.MAX_VALUE);
        POCKET_GENERATOR_BURN_SPEED_MULTIPLIER = COMMON_BUILDER.comment("The multiplier for the burn speed, making the generator run faster. Coal is 1600 ticks to burn, if you set this to 10, it will burn in 160 ticks")
                .defineInRange("pocket_gen_burn_speed_multiplier", 4, 1, 1000);
        POCKET_GENERATOR_MAX_FE = COMMON_BUILDER.comment("The maximum amount of Forge Energy the generator can hold in its buffer")
                .defineInRange("pocket_gen_max_fe", 1000000, 1, Integer.MAX_VALUE);
        POCKET_GENERATOR_FE_PER_TICK = COMMON_BUILDER.comment("The FE per Tick that the generator charges other items at")
                .defineInRange("pocket_gen_fe_per_tick", 5000, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }
}
