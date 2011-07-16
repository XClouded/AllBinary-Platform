/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.game.displayable.canvas;

import allbinary.canvas.Processor;
import allbinary.graphics.paint.NullPaintable;

public class EndGameProcessor extends Processor
{
    private AllBinaryGameCanvas gameCanvas;

    private final int WAIT = 5000;

    public EndGameProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }
    
    public void process() throws Exception
    {
        // Only Show End of game for people
        if (this.gameCanvas.isHighScoreSubmitted())
        {
            // LogUtil.put(LogFactory.getInstance("Score Submitted: Time In State: "
            // +
            // this.getGameStateTimeHelper().getElapsed(), this,
            // "showEndOfGame"));
            if (this.gameCanvas.getGameStateTimeHelper().isElapsed(WAIT))
            {
                if (this.gameCanvas.getGameState() == AllBinaryGameCanvas.SHOW_END_RESULT_GAME_STATE)
                {
                    this.gameCanvas.setGameState(AllBinaryGameCanvas.SHOW_HIGH_SCORE_GAME_STATE);
                    this.gameCanvas.setHighScoresPaintable(this.gameCanvas.getRealHighScoresPaintable());
                }
                else if (this.gameCanvas.getGameState() == AllBinaryGameCanvas.SHOW_HIGH_SCORE_GAME_STATE)
                {
                    this.gameCanvas.setGameState(AllBinaryGameCanvas.SHOW_END_RESULT_GAME_STATE);
                    this.gameCanvas.setHighScoresPaintable(NullPaintable.getInstance());
                }
            }
        }
    }
}
