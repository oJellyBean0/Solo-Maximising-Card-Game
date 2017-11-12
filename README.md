# Solo-Maximising-Card-Game

The standard playing cards^1 used in many games has four suits (clubs, diamonds, hearts and spades)
and thirteen ranks (2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king and ace). A deck of cards has one card
for each suit and rank combination. In many games there are two extra cards called jokers, which do
not belong to any suit. For the purposes of this task, you may consider a joker to have a suit of
“none” and a rank of “joker”.

The value of a card is determined by the rank of a card. If the rank is a number, then this is the card’s
value, i.e. the 2 of hearts has a value of 2, etc. If the rank is jack, queen or king, then the card’s value
is 10. If the card’s rank is ace, then its value is 11. If the card’s rank is joker, then its value is 20.

Write a console application that uses the console-menu classes developed in the previous
assignments (if you want too) to play the following game:

1) Initially a deck of cards is created and shuffled;
2) The player starts with a score of 0, the aim is to reach as high a score as possible for a given
deck;
3) A round^2 of play consist of:
a) The player is dealt the top 3 cards
b) The computer is dealt the next 3 cards
c) The player must “match” one of his cards with one of the computer’s. In order for cards to
“match”, they need to have either the same suit _or_ the player’s card’s value is the same or
higher than the computer’s card’s value, e.g. a player’s 10 of spades matches a computer’s
queen of spades (same suit), while a player’s 10 of spades matches a computer’s 5 of hearts
(10 > 5). Note that a player’s joker card can match any of the computer’s cards, _but_ a
computer’s joker card can only be matched by a player joker card^3. If there is a match, then
the value of the player’s card is added to the player’s score and both cards are discarded.
d) The player may match as many of his cards as he wishes (obviously will try to match all).
e) At the end of the round, the player’s score is adjusted by
i) Subtracting the value of the player’s remaining cards from the score
ii) Subtracting the value of the computer’s remaining cards from the score
f) Any remaining computer and player cards are discarded before the start of the new round.
4) Once all cards have been played (i.e. no cards left at the start of a round), then the score for that
deck is the player’s score.

While the game is being played, the current deck, score, hands, round, etc. need to be saved. At any
time, the player has the option to “rewind” to an earlier round to resume play from there.
Rewinding incurs a penalty of 5 per round going back from the current round to the player’s score.

The player may also choose to restart, in which case the game is started from the beginning again
with no score penalty. Every time a deck is played, the previous maximum score obtained for the
deck needs to be displayed (the player needs to try and beat this score).

If the program is ended and re-run and there exists a previous save game, then the player may
choose to resume the previous game, as opposed to starting a new game.

(^1) See https://en.wikipedia.org/wiki/Standard_52-card_deck
