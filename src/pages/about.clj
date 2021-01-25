(ns pages.about)


(defn content []
  (list
   [:h2 "Who I am & what I do"]
   [:p "I'm Tercio, a brazilian software tinkerer currently based in São Paulo."
    " I'm employed by "
    [:a {:href "https://nubank.com.br/" :style {:color "rgb(138, 5, 190)" :font-weight "bold"}} "Nubank"]
    " as a Systems Engineer at the Data Access team."
    " In my time I've been mostly working in a middle ground area of software engineering and systems administration."
    " My main interests are distributed systems and systems programming, so there you go; though I like pretty things."]
   [:h2 "Contact"]
   [:p "If you wanna talk to me, feel free to reach me via "
    [:a {:href "mailto:contact@tercio.com.br"} "contact@tercio.com.br"]
    ". You may also follow me on "
    [:a {:href "https://twitter.com/terciodemelo"} "Twitter"]
    ", even though I'm not actually active there, and as Brazil is bursting into fascist flames I mostly retweet politics."
    " You will also find me at "
    [:a {:href "https://www.linkedin.com/in/tercio-de-melo/"} "LinkedIn"]
    ", but it is just a resumé hosting platform to me."
    " The truth is that I'm not very much engaged to social media anymore. I'm tired and bored by it;"
    " and have also been trying to dodge corporate spying one step at a time."]))
