package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.theexceptionist.coherentvillages.entity.soldier.AbstractVillagerSoldier;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerGuard;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerManAtArms;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerMilitia;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerSergeant;
import net.theexceptionist.coherentvillages.entity.soldier.EntityVillagerWarrior;

public class VillageComponentBarracks extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;

        public VillageComponentBarracks()
        {
        }

        public VillageComponentBarracks(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
        }

        public static VillageComponentBarracks createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 7, 6, 7, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentBarracks(start, p_175858_7_, rand, structureboundingbox, facing);
        }

        /**
         * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
        	//System.out.println("Generating"+this.getXWithOffset(0, 0)+" "+this.getZWithOffset(0, 0));
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4, 0);
            }
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 6, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 0, 6, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 0, 5,iblockstate1, iblockstate1, false);
            
            this.setBlockState(worldIn, iblockstate5, 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 5, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 3, 0, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn, iblockstate6, 2, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 2, 4, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 4, 0, structureBoundingBoxIn);
            
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn,randomIn, 3, 1, 0, EnumFacing.NORTH);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 1, -1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 2, -1, structureBoundingBoxIn);
            
            this.placeTorch(worldIn, EnumFacing.NORTH, 3, 3, 2, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 3, 3, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 5, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 0, structureBoundingBoxIn);
            
            if(randomIn.nextInt(2) == 0){
            	this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), 2, 1, 2, structureBoundingBoxIn);
            }
            
            if(randomIn.nextInt(2) == 0){
            	this.setBlockState(worldIn, iblockstate, 4, 1, 2, structureBoundingBoxIn);
            	this.setBlockState(worldIn, Blocks.BREWING_STAND.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            	this.setBlockState(worldIn, Blocks.CAULDRON.getDefaultState(), 5, 1, 2, structureBoundingBoxIn);
            }
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 1, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 4, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 6, structureBoundingBoxIn);
            
            
            //
            
            
            this.setBlockState(worldIn, iblockstate5, 6, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 3, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 1, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 2, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 5, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 4, 5, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 1, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 1, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 5, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 5, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 2, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 3, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS.getDefaultState(), 4, 2, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate6, 2, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 4, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 3, 5, 6, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 1, 1, 6, 3, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 6, iblockstate, iblockstate, false);
            
            this.setBlockState(worldIn, iblockstate5, 3, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 3, 5, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 6, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 6, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate5, 0, 1, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate5, 0, 3, 6, structureBoundingBoxIn);
            
            if (this.getBlockStateFromPos(worldIn, 3, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 3, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate2, 3, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 3, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
                }
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);


            this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);

            for (int j = 0; j < 7; ++j)
            {
                for (int i = 0; i < 7; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 6, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.PLANKS.getDefaultState(), i, -1, j, structureBoundingBoxIn);
                }
            }
            if(!worldIn.isRemote){
            this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 3 + randomIn.nextInt(3));
            }
            return true;
        }
        
        protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count)
        {
            if (this.villagersSpawned < count)
            {
                for (int i = villagersSpawned; i < count; ++i)
                {
                    int j = this.getXWithOffset(x + i, z);
                    int k = this.getYWithOffset(y);
                    int l = this.getZWithOffset(x + i, z);

                    if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                    {
                        break;
                    }

                    ++this.villagersSpawned;

                    AbstractVillagerSoldier entityvillager = new EntityVillagerMilitia(worldIn);
             
                    
                    if(worldIn.rand.nextInt(100) <= 5){
                    	entityvillager = new EntityVillagerSergeant(worldIn);
                     }
                    else if(worldIn.rand.nextInt(100) <= 50)
                    {
                    	entityvillager = new EntityVillagerManAtArms(worldIn);
                    }
                    else if(worldIn.rand.nextInt(100) <= 60)
                    {
                    	entityvillager = new EntityVillagerWarrior(worldIn);
                    }else if(worldIn.rand.nextInt(100) <= 75)
                    {
                    	entityvillager = new EntityVillagerGuard(worldIn);
                    }
                    
                    entityvillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(j, k, l)), null);
                	entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setSpawnPoint((double)j + 0.5D, (double)k, (double)l + 0.5D);
                    //entityvillager.setProfession(null);
                    
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
            }
        }
    
}
