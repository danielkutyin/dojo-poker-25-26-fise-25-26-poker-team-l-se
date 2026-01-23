package fr.cotedazur.univ.polytech.startingpoint.takenoko.characters;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.Actions;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.Actions.RandomStrategy;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.exceptions.ArgumentalreadyExistOrnotAdj;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.board.*;

import fr.cotedazur.univ.polytech.startingpoint.takenoko.Objectives.PandaObjective;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.GardnerObjectives;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesDeck;
import fr.cotedazur.univ.polytech.startingpoint.takenoko.objectives.ObjectivesPanda;

import java.util.*;

public class Robot {

    private static int nextId = 1;
    private final Random random = new Random();
    private Board board;
    private final String name;
    private int score = 0;
    private final EnumMap<TileColor, Integer> eatenBamboos = new EnumMap<>(TileColor.class);
    private List<PandaObjective> pandaObjectives = new ArrayList<>();
    private List<GardnerObjectives> cardsgardner = new ArrayList<>();

    private static final int MAX_OBJECTIVES_IN_HAND = 5;



    public Robot(Board board) {
        this.board = board;
        this.name = "Robot" + (nextId++);
        for (TileColor c : TileColor.values()) {
            eatenBamboos.put(c, 0);
        }
    }
    public void drawPandaObjective(ObjectivesPanda pandaDeck) {
        if (totalObjectivesInHand() >= MAX_OBJECTIVES_IN_HAND) {
            System.out.println(name + " ne peut pas piocher plus d'objectifs panda (limite atteinte).");
            return;
        }
        pandaDeck.draw().ifPresent(card -> {
            pandaObjectives.add(card);
            System.out.println(name + " pioche un objectif panda : " + card);
        });
    }

    public void drawGardenerObjective(ObjectivesDeck gardenerDeck) {
        if (totalObjectivesInHand() >= MAX_OBJECTIVES_IN_HAND) {
            System.out.println(name + " ne peut pas piocher plus d'objectifs jardinier (limite atteinte).");
            return;
        }
        gardenerDeck.draw().ifPresent(card -> {
            cardsgardner.add(card);
            System.out.println(name + " pioche un objectif jardinier : " + card);
        });
    }
    public List<PandaObjective> getPandaObjectives() {
        return Collections.unmodifiableList(pandaObjectives);
    }

    public boolean canDrawObjective() {
        return totalObjectivesInHand() < MAX_OBJECTIVES_IN_HAND;
    }

    public int totalObjectivesInHand() {
        return pandaObjectives.size() + cardsgardner.size();
    }


    private void addEaten(TileColor c) {
        if (c == null || c == TileColor.POND) return;
        eatenBamboos.put(c, eatenBamboos.getOrDefault(c, 0) + 1);
    }

    public int getEaten(TileColor c) {
        return eatenBamboos.getOrDefault(c, 0);
    }
    public Map<TileColor, Integer> getEatenBamboos() {
        return new EnumMap<>(eatenBamboos);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int delta) {
        this.score += delta;
    }

    /**
     * le robot donne l'ordre à l'engine pour placer la tuile aux coord choisies et récuperer la position
     **/


    public void playTileTurn() {

        List<Position> playable = board.getPlayablePositions();
        if (playable.isEmpty()) {
            System.out.println(name + " n'a aucune position jouable.");
            return;
        }
        Position chosen = playable.get(random.nextInt(playable.size()));
        Tile tile = new Tile(chosen, TileColor.GREEN);
        board.addTile(tile);
        addScore(1);
        System.out.println(name + "placer tuile à " + chosen + " score =" + score);


/*        Position pondPos = board.getPond().getPosition();
        List<Position> voisins = board.setNeighbours(pondPos);
        List<Position> libres = board.listBon(voisins);

        if (libres.isEmpty()) {
            System.out.println("Full");
            return;
        }
        Position chosen = libres.get(random.nextInt(libres.size()));
        board.addTile(new Tile(chosen, TileColor.GREEN));
        addScore(1);
        System.out.println(name + "place tile at" + chosen + "score =" + score);
    }*/
//    public void placeTileAdEtang (){
//
//        Position pond = this.board.getPond().getPosition();
//        //Position[] freePositions = new Position[Position.adjacentPos.size()];
//        //int freeCount=0;
//        //collecte les positions libres a cote de l etang
//
//        /*for(int i=0;i<Position.adjacentPos.size();i++){
//            Position candidate=pond.getNeighbourPosbyindex(i);
//            if(board.getTileAt(candidate)==null){
//                freePositions[freeCount++]=candidate;
//            }
//        }
//
//        //plus de places disponibles
//        if(freeCount==0){
//            System.out.println("Plus de places autour de l etang");
//            return;
//        }
//
//        Position chosen=freePositions[random.nextInt(freeCount)];
//
//        TileColor[] values=TileColor.values();
//        int colorIndex=random.nextInt(values.length-1);
//        TileColor color=values[colorIndex];
//*/
//        Position chosen=new Position(random.nextInt(3),random.nextInt(3));
//        board.addTile(new Tile(chosen,TileColor.GREEN));
//        addScore(1);
//        System.out.println(name + " pose une tuile  en "+ chosen + " | score = "+score);
  }

  /* public Position playPandaMove(){
        int range =  new Random().nextInt(6)+1;//pour ne pas choisir 0
        Panda po = this.board.getPanda();
        List<Direction> directionsAvailable = new ArrayList<>();
        for(int i =0;i<6;i++){
            if(Direction.rangeMovement(po.getPos(),Direction.values()[i]) >0) directionsAvailable.add(Direction.values()[i]);
        }
        int i = new Random().nextInt(directionsAvailable.size());
        Direction moved = directionsAvailable.get(i);
        int randMove = new Random().nextInt(Direction.rangeMovement(po.getPos(),directionsAvailable.get(i)));
        Position placed = moved.move(po.getPos(),moved,randMove);
        if (board.isExistInTiles(placed)) {
            po.setPos(placed);
            return placed;
        }
        else{
            throw new ArgumentalreadyExistOrnotAdj("Tile Does not exists " + placed);
        }
    }*/
    public Position playPandaMove() {
        Panda po = this.board.getPanda();
        Position start = po.getPos();

        List<Direction> directionsAvailable = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (board.rangeMovement(start, dir) > 0) directionsAvailable.add(dir);
        }

        if (directionsAvailable.isEmpty()) {
            throw new ArgumentalreadyExistOrnotAdj("No valid direction for Panda from " + start);
        }

        Direction moved = directionsAvailable.get(random.nextInt(directionsAvailable.size()));
        int maxRange = board.rangeMovement(start, moved);
        int dist = random.nextInt(maxRange) + 1; // distance minimale = 1

        Position placed = Direction.move(start, moved, dist);

        if (board.isExistInTiles(placed)) {
            po.setPos(placed);
            return placed;
        } else {
            throw new ArgumentalreadyExistOrnotAdj("Tile Does not exist " + placed);
        }
    }

    public void playPandaTurn(){
        System.out.println("Robot " + name + " chooses to move panda.");
        while (true){
            try{
                Position place =playPandaMove();
                System.out.println("panda moves to " + place.toString());
                if (!(board.getTileAt(place).TileWithoutBambousOrPond())){
                    System.out.println("panda eats Bambou in Tile " + place );
                    TileColor c = board.getTileAt(place).getColor();
                    boolean ate = board.tryEatBambouOnPandaTile();
                    if (ate) addEaten(c);
                    System.out.println("there is now in tile" + place + " total = " + board.getTileAt(place).getNbBambous());
                }
                break;
            }
            catch(ArgumentalreadyExistOrnotAdj e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void playTurn(ObjectivesPanda pandaDeck, ObjectivesDeck gardenerDeck) {
        RandomStrategy strategy = new RandomStrategy();
        Actions a1 = strategy.choose(new TurnView(board, this));
        applyAction(a1, pandaDeck, gardenerDeck);

        Actions a2 = strategy.choose(new TurnView(board, this));

        applyAction(a2, pandaDeck, gardenerDeck);
    }

    public void applyAction(Actions action, ObjectivesPanda pandaDeck, ObjectivesDeck gardenerDeck) {
        if (board.getNumTiles() < 3 && action != Actions.Tiles) action = Actions.Tiles;

        switch (action) {
            case Tiles -> playTileTurn();
            case Panda -> playPandaTurn();
            case Gardener -> playGardenerTurn();
            case Objectives -> playObjectiveTurn(pandaDeck, gardenerDeck);
        }
    }
    public void playObjectiveTurn(ObjectivesPanda pandaDeck, ObjectivesDeck gardenerDeck) {
        if (totalObjectivesInHand() >= MAX_OBJECTIVES_IN_HAND) {
            System.out.println(name + " ne peut pas piocher (main pleine).");
            return;
        }

        boolean canPanda = pandaDeck.remaining() > 0;
        boolean canGardener = gardenerDeck.remaining() > 0;

        if (!canPanda && !canGardener) {
            System.out.println(name + " : plus d'objectifs à piocher.");
            return;
        }

        // choix random entre les piles disponibles
        if (canPanda && (!canGardener || random.nextBoolean())) {
            drawPandaObjective(pandaDeck);
        } else {
            drawGardenerObjective(gardenerDeck);
        }
    }






    public Position playGardenerMove() {
        Gardener g = this.board.getGardener();
        Position start = g.getPos();

        /** Chercher les directions où un mouvement est possible **/

        List<Direction> directionsAvailable = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (board.rangeMovement(start, dir) > 0) {
                directionsAvailable.add(dir);
            }
        }


        if (directionsAvailable.isEmpty()) {
            throw new ArgumentalreadyExistOrnotAdj("No valid direction for Gardener from " + start);
        }

        /**  Choisir une direction valide **/

        Direction moved = directionsAvailable.get(new Random().nextInt(directionsAvailable.size()));

        /**  Choisir une distance valide **/

        int maxRange = board.rangeMovement(start, moved);
        int dist = new Random().nextInt(maxRange) + 1;

        /**  Calculer la position finale **/

        Position placed = Direction.move(start, moved, dist);

        /** Vérifier la tuile existe **/

        if (board.isExistInTiles(placed)) {
            g.setPos(placed);
            System.out.println("Gardener moves from " + start  + " dir=" + moved + " dist=" + dist + " to " + placed);
            return placed;
        } else {
            throw new ArgumentalreadyExistOrnotAdj("Tile does not exist " + placed);
        }


    }

    public void playGardenerTurn() {
        System.out.println("Robot " + name + " choisit de déplacer le jardinier.");
        while (true) {
            try {
                Position p = playGardenerMove();
                System.out.println("Jardinier déplacé en " + p);
                board.plantBambooOnGardenerTile();
                if (!(board.getTileAt(p).getColor().equals(TileColor.POND))) {
                    System.out.println("Un bambou pousse sur la tuile " + board.getGardener().getPos()
                            + " (total = " + board.getTileAt(p).getNbBambous() + ")");
                }
                break;
            }
            catch (ArgumentalreadyExistOrnotAdj e) {
                System.out.println("Déplacement jardinier impossible : " + e.getMessage());
            }
        }
    }
    public void removeachieved (GardnerObjectives card , Board board){
        if  (card.isAchieved(board)){
            cardsgardner.remove(card);
        }
    }



}

