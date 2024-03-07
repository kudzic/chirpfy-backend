package com.kudzi.TweetService.services;

import com.kudzi.TweetService.dto.TweetDto;
import com.kudzi.TweetService.entity.Tweet;
import com.kudzi.TweetService.mapper.TweetMapper;
import com.kudzi.TweetService.repository.TweetRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;


    public void addTweet(Tweet tweet){
        tweetRepository.save(tweet);

    }

    public List<Tweet> getAllTweets(){
        return  tweetRepository.findAll();
    }

    public Tweet getTweet(String TweetId){
     return tweetRepository.findById(TweetId).orElseThrow(()-> new NotFoundException());
    }

    public void deleteTweet(String TweetId)throws Exception{
        if(tweetRepository.existsById(TweetId)){
            tweetRepository.deleteById(TweetId);
            return;
        }
        throw new Exception("The tweet doesn't exist");
    }

    public Long likeTweet(String TweetId){
        Optional<Tweet> tweet=tweetRepository.findById(TweetId);

        Tweet actual=tweet.get();
        if(actual.replied_id==null) {
            actual.setLikes(actual.likes + 1);
            tweetRepository.save(actual);
        }
        else{
            Optional<Tweet> parent=tweetRepository.findById(actual.replied_id);
            Tweet current_tweet=parent.get();
            ArrayList<Tweet> replies=current_tweet.getReplies();
            replies.remove(actual);
            actual.setLikes(actual.likes + 1);

            replies.add(actual);
            current_tweet.setReplies(replies);

            tweetRepository.save(current_tweet);
            tweetRepository.save(actual);
        }
        return actual.getLikes();
    }

    public Long unlikeTweet(String TweetId){
        Optional<Tweet> tweet=tweetRepository.findById(TweetId);

        Tweet actual=tweet.get();
        if(actual.replied_id==null) {
            if(actual.likes>0) {
                actual.setLikes(actual.likes - 1);
            }
            else{
                actual.setLikes(0L);
            }

            tweetRepository.save(actual);
        }
        else{
            Optional<Tweet> parent=tweetRepository.findById(actual.replied_id);
            Tweet current_tweet=parent.get();
            ArrayList<Tweet> replies=current_tweet.getReplies();
            replies.remove(actual);
            if(actual.likes>0) {
                actual.setLikes(actual.likes - 1);
            }
            else{
                actual.setLikes(0L);
            }
            replies.add(actual);
            current_tweet.setReplies(replies);

            tweetRepository.save(current_tweet);
            tweetRepository.save(actual);
        }



        tweetRepository.save(actual);

        return actual.likes;
    }

    public List<Tweet> tweetReplies(String Tweetid, TweetDto Tweet){
        Optional<Tweet> tweet=tweetRepository.findById(Tweetid);
        Tweet tweet1=tweet.get();
        ArrayList<Tweet> replies=tweet1.getReplies();
        TweetMapper tweetMapper=new TweetMapper();
        Tweet tweet2=tweetMapper.mapToTweet(Tweet,new Tweet());
        tweet2.setReplied_id(tweet1.tweet_id);
        replies.add(tweet2);
        tweet1.setReplies(replies);
        tweetRepository.save(tweet2);
        tweetRepository.save(tweet1);


        return tweet1.replies;
    }


}
