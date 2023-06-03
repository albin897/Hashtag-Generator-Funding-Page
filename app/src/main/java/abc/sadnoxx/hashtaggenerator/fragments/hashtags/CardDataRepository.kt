package abc.sadnoxx.hashtaggenerator.fragments.hashtags

import abc.sadnoxx.hashtaggenerator.fragments.hashtags.Card

object CardDataRepository {
    val cardDataList = listOf(
        Card("Card 1", listOf("tag1", "tag2", "tag3")),
        Card("Card 2", listOf("tag4", "tag5")),
        Card("Card 3", listOf("tag6", "tag7", "tag8", "tag9")),
        Card(
            "Viral", listOf(
                "#viral", "#trending", "#viralvideos", "#explorepage", "#explore",
                "#viralpost", "#foryou", "#tiktok", "#reels", "#instagram", "#fyp",
                "#trend", "#foryoupage", "#viralvideo", "#followforfollowback",
                "#instagood", "#likeforlikes", "#reelsinstagram", "#love", "#video",
                "#trendingnow", "#viralindonesia", "#reelitfeelit", "#viralreels",
                "#exploremore", "#instadaily", "#views", "#post", "#memes", "#india"
            )
        )
        // Add more CardData objects as needed
    )
}
