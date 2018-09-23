package net.theexceptionist.coherentvillages.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.Template;
import net.theexceptionist.coherentvillages.entity.EntityVillagerAlchemist;
import net.theexceptionist.coherentvillages.entity.EntityVillagerMage;

public class VillageComponentInn extends StructureVillagePieces.Village
    {
        private boolean isRoofAccessible;
		private int villagersSpawned;
		private Template template;

        public VillageComponentInn()
        {
        }

        public VillageComponentInn(StructureVillagePieces.Start start, int p_i45566_2_, Random rand, StructureBoundingBox p_i45566_4_, EnumFacing facing)
        {
            super(start, p_i45566_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45566_4_;
            this.isRoofAccessible = rand.nextBoolean();
            
        }

        public static VillageComponentInn createPiece(StructureVillagePieces.Start start, List<StructureComponent> p_175858_1_, Random rand, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing facing, int p_175858_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 15, 12, 16, facing);
            return StructureComponent.findIntersecting(p_175858_1_, structureboundingbox) != null ? null : new VillageComponentInn(start, p_175858_7_, rand, structureboundingbox, facing);
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

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
            }
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 14, 12, 15, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            IBlockState iblockstate7 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
            
            //IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.STONEBRICK.getDefaultState());
            IBlockState iblockstate8 = this.getBiomeSpecificBlockState(Blocks.GLASS.getDefaultState());
            


           /* BlockPos basePos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0));
            //BlockPos basePos = new BlockPos(this.getBoundingBox().minX, worldIn.getTopSolidOrLiquidBlock(new BlockPos(this.getBoundingBox().minX, 80, this.getBoundingBox().minZ)).getY(), this.getBoundingBox().minZ);
            ModGenBiomeProcessor processor = new ModGenBiomeProcessor(worldIn.getBiome(basePos));
            template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), ModMapVillageGen.INN_RESOURCE);
            template.addBlocksToWorld(worldIn, basePos, processor, ModMapVillageGen.DEFAULT_SETTINGS, 2);*/
           // template.
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 14, 0, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 14, 1, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 13, 0, 14, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 13, 1, 14, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 0, 14, 1, 2, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 4, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 14, 4, 6, iblockstate, iblockstate, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 14, 5, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 13, 5, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 13, 4, 5, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 0, 14, 6, 5, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 6, 1, 13, 6, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
 
            this.setBlockState(worldIn,iblockstate3, 11, 0, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate3, 11, 0, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate1, 11, 0, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate2, 12, 0, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 13, 0, 3, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 0, 0, 14, 5, 0, iblockstate, iblockstate, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 1, 7, 14, 8, 14, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 7, 0, 8, 14, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 15, 13, 8, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 6, 13, 8, 6, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 0, 6, 14, 8, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 6, 0, 8, 6, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 14, 0, 15, 14, 8, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 15, 0, 8, 15, iblockstate, iblockstate, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 9, 6, 14, 9, 15, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 9, 7, 13, 9, 14, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
           
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 10, 6, 14, 10, 15, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 10, 7, 13, 10, 14, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            
            //Wool
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 7, 13, 4, 14, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 7, 13, 4, 14, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 8, 7, 13, 8, 14, iblockstate1, iblockstate1, false);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 6, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 2, 5, 6, EnumFacing.NORTH);
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 2, 3, 5, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 2, 7, 5, structureBoundingBoxIn);
	        
	        this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 7, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 2, 7, 7, structureBoundingBoxIn);
	        
	        //
	        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 9, 14, 1, 9, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 9, 14, 2, 9, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 9, 14, 3, 9, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 4, 1, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 1, 9, 7, 1, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 1, 9, 10, 1, 15, iblockstate1, iblockstate1, false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 9, 4, 2, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 2, 9, 7, 2, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 2, 9, 10, 2, 15, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 9, 4, 3, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 9, 7, 3, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, 9, 10, 3, 15, iblockstate1, iblockstate1, false);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 3, 1, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 6, 1, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 9, 1, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 11, 1, 9, EnumFacing.NORTH);
	       
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 3, 3, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 3, 3, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 6, 3, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 6, 3, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 9, 3, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 9, 3, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 11, 3, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 11, 3, 10, structureBoundingBoxIn);

	        this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 2, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 2, 1, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 5, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 5, 1, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 8, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 8, 1, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 12, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 12, 1, 14, structureBoundingBoxIn);
            
	        //

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 9, 14, 5, 9, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 9, 14, 6, 9, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 9, 14, 7, 9, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 9, 4, 5, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 5, 9, 7, 5, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 5, 9, 10, 5, 15, iblockstate1, iblockstate1, false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 9, 4, 6, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 6, 9, 7, 6, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 6, 9, 10, 6, 15, iblockstate1, iblockstate1, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 7, 9, 4, 7, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 7, 9, 7, 7, 15, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 7, 9, 10, 7, 15, iblockstate1, iblockstate1, false);
            
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 3, 5, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 6, 5, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 9, 5, 9, EnumFacing.NORTH);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 11, 5, 9, EnumFacing.NORTH);
	       
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 3, 7, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 3, 7, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 6, 7, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 6, 7, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 9, 7, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 9, 7, 10, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.SOUTH, 11, 7, 8, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 11, 7, 10, structureBoundingBoxIn);

	        this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 2, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 2, 5, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 5, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 5, 5, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 8, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 8, 5, 14, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT), 12, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD), 12, 5, 14, structureBoundingBoxIn);
            
	        
	        
	        this.setBlockState(worldIn,iblockstate8, 0, 6, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 0, 7, 8, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 14, 6, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 14, 7, 8, structureBoundingBoxIn);
           
            this.setBlockState(worldIn,iblockstate8, 0, 2, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 0, 3, 8, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 14, 2, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 14, 3, 8, structureBoundingBoxIn);
            
            
            this.setBlockState(worldIn,Blocks.AIR.getDefaultState(), 3, 4, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,Blocks.AIR.getDefaultState(), 4, 4, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,Blocks.AIR.getDefaultState(), 5, 4, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,Blocks.AIR.getDefaultState(), 6, 4, 7, structureBoundingBoxIn);
            
            //this.setBlockState(worldIn,iblockstate1, 3, 4, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate1, 4, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate1, 5, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate1, 6, 3, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate7, 3, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate7, 4, 2, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate7, 5, 3, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate7, 6, 4, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 5, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 5, 7, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 7, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 7, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 5, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 2, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 5, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 3, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 5, 6, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 6, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 6, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 5, 7, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 7, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 7, 15, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate8, 5, 2, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 2, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 2, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 5, 3, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 8, 3, 15, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate8, 11, 3, 15, structureBoundingBoxIn);
            
            this.placeTorch(worldIn, EnumFacing.NORTH, 14, 3, 1, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.WEST, 13, 3, 0, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.EAST, 1, 3, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.NORTH, 0, 3, 1, structureBoundingBoxIn);
	        
	        this.placeTorch(worldIn, EnumFacing.SOUTH, 14, 3, 5, structureBoundingBoxIn);
	        
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 7, 5, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 14, 7, 5, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.UP, 0, 7, 0, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 14, 7, 0, structureBoundingBoxIn);
//
	        this.placeTorch(worldIn, EnumFacing.UP, 0, 11, 6, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 14, 11, 6, structureBoundingBoxIn);

	        this.placeTorch(worldIn, EnumFacing.UP, 0, 11, 15, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.UP, 14, 11, 15, structureBoundingBoxIn);

            //Ladder
	        this.setBlockState(worldIn,Blocks.AIR.getDefaultState(), 1, 8, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 7, 1, 8, 7, Blocks.LADDER.getDefaultState(), Blocks.LADDER.getDefaultState(), false);
            
            
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 0, 5,iblockstate1, iblockstate1, false);
            /*this.setBlockState(worldIn,iblockstate2, 0, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 1, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 2, 0, 0, structureBoundingBoxIn);
       
            this.setBlockState(worldIn,iblockstate, 0, 0, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 1, 0, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 2, 0, 1, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate2, 0, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 1, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 2, 1, 1, structureBoundingBoxIn);
       
            this.setBlockState(worldIn,iblockstate, 0, 0, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 1, 0, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 2, 0, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate, 0, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 1, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 2, 1, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate2, 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 1, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate2, 2, 2, 2, structureBoundingBoxIn);
       
            this.setBlockState(worldIn,iblockstate4, 0, 0, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 0, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 0, 0, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 0, 1, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 3, 0, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 3, 1, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 7, 0, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 7, 1, 7, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 3, 0, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 3, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 3, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 3, 1, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 7, 0, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 7, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn,iblockstate4, 7, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate4, 7, 1, 0, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 2, 0, 7, 2, 7,iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 2, 1, 6, 2, 6,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 3, 2, 2, 7,iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 4, 2, 2, 6,iblockstate1, iblockstate1, false);
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 4, 7, 6, 7,iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 5, 6, 6, 6,Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 6, 5, 6, 6, 6,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 5, 3, 4, 5,Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 3, 4, 4, 4, 4,Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
	        
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 3, 3, 5, EnumFacing.EAST);
	        this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 4, 3, 4, EnumFacing.NORTH);
	        
	        this.placeTorch(worldIn, EnumFacing.WEST, 2, 5, 5, structureBoundingBoxIn);
	        this.placeTorch(worldIn, EnumFacing.EAST, 4, 5, 5, structureBoundingBoxIn);
	        
	        this.setBlockState(worldIn,Blocks.CAULDRON.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn,iblockstate, 5, 3, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn,Blocks.BREWING_STAND.getDefaultState(), 5, 4, 6, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 3, 5, 6, 6, 6,iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, 3, 0, 3, 7,iblockstate4, iblockstate4, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 7, 2, 3, 7,iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 3, 0, 7, 3, 0,iblockstate4, iblockstate4, false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 3, 0, 7, 3, 3,iblockstate4, iblockstate4, false);*/
	        //6, 3, 5,/* //Stair Stab Piece
            //x, y , z
           /* this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 0, 0, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 4, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 3, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 2, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 0, 13, structureBoundingBoxIn);
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 0, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 11, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 6, structureBoundingBoxIn);
            
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 4, 12, Blocks.STONEBRICK.getDefaultState(), Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 1, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 1, 12, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 12, 1, 5, 12, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 1, 5, 1, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 12, 12, 5, 12, Blocks.DOUBLE_STONE_SLAB.getDefaultState(),Blocks.DOUBLE_STONE_SLAB.getDefaultState(), false);
            
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 2, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 3, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 3, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 5, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 5, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 6, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 6, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 7, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 7, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 8, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 8, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 9, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 9, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 12, structureBoundingBoxIn);
            
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 4, 5, 12, structureBoundingBoxIn);
            //this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 10, 5, 12, structureBoundingBoxIn);
            //Stair Stab Piece
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 13, 0, 0, structureBoundingBoxIn);
           
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 0, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 0, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 1, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 2, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 3, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 3, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 4, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 4, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 12, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
           
            
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 5, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 4, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 3, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 2, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 1, 13, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.DOUBLE_STONE_SLAB.getDefaultState(), 12, 0, 13, structureBoundingBoxIn);
            
            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 12, 3, 12, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            
           /* this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 9, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 10, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 0, 11, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 8, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2.withProperty(BlockStairs.FACING, EnumFacing.WEST), 1, 3, 9, structureBoundingBoxIn);

  
            
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 10, 5, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, iblockstate4, 10, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 10, 6, 7, structureBoundingBoxIn);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 0, 2, 11, 0, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 1, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 1, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 2, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 2, 3, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 5, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.IRON_BARS.getDefaultState(), 11, 2, 6, structureBoundingBoxIn);
            
            this.setBlockState(worldIn, Blocks.STONEBRICK.getDefaultState(), 11, 2, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 3, 2, 11, 3, 11, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 11, 4, 2, 11, 4, 7, Blocks.STONEBRICK.getDefaultState(), Blocks.STONEBRICK.getDefaultState(), false);
            
            this.setBlockState(worldIn, iblockstate4, 11, 6, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate4, 11, 6, 7, structureBoundingBoxIn);
            */
            /*
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 0, 4, 4, 4, iblockstate3, iblockstate3, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 3, 4, 3, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, iblockstate, 0, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 0, 3, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 4, 3, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, iblockstate1, iblockstate1, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 4, 3, 3, 4, iblockstate1, iblockstate1, false);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 2, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 1, 0, structureBoundingBoxIn);

            
            if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
              //  this.setBlockState(worldIn, iblockstate2, 2, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 2, -1, -1, structureBoundingBoxIn);
                }
            }*/

            //this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 3, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

            /*if (this.isRoofAccessible)
            {
                this.setBlockState(worldIn, iblockstate4, 0, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 0, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 1, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 2, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 3, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 4, 5, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate4, 0, 5, 3, structureBoundingBoxIn);
            }

            if (this.isRoofAccessible)
            {
                IBlockState iblockstate5 = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH);
                this.setBlockState(worldIn, iblockstate5, 3, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 3, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate5, 3, 4, 3, structureBoundingBoxIn);
            }*/

            this.placeTorch(worldIn, EnumFacing.NORTH, 2, 3, 1, structureBoundingBoxIn);

            for (int j = 0; j < 15; ++j)
            {
                for (int i = 0; i < 14; ++i)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, i, 12, j, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.PLANKS.getDefaultState(), i, -1, j, structureBoundingBoxIn);
                }
            }
            if(!worldIn.isRemote){
            	this.spawnVillagers(worldIn, structureBoundingBoxIn, 1, 1, 2, 5 + randomIn.nextInt(5));
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
                    
                   // if(worldIn.rand.nextInt(100) <= 20){
                    EntityVillager entityvillager = new EntityVillager(worldIn);
                    entityvillager.setLocationAndAngles((double)j + 1.5D, (double)k + 5D, (double)l + 0.5D, 0.0F, 0.0F);
                    entityvillager.setProfession(worldIn.rand.nextInt(6));                               
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                
                       
                }
            }
        }
    
}