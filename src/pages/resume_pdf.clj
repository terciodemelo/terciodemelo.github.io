(ns pages.resume-pdf)

(defn content []
  [:div.resume
   [:div.heading
    [:h1 "Tercio de Melo"]
    [:a {:href "mailto:contact@tercio.com.br"} "contact@tercio.com.br"]
    [:p.location "Berlin - Germany"]]
   
   [:h2 "LONG STORY SHORT"]
   [:p
    "I'm a Software Engineer with a strong background on cloud infrastructure "
    "and distributed systems with intensive data requirements. I've started my "
    "professional career building backend systems in the mobile gaming industry, "
    "handling traffic of millions of players at the unicorn "
    [:a {:href "https://wildlifestudios.com"} "Wildlife Studios"]
    ". In the sequence I spent about an "
    "year as contractor stabilizing cloud infrastructure of growing companies in "
    "Silicon Valley and Brazil. Then I worked for three years in e-commerce, both "
    "being part of the launch of "
    [:a {:href "https://amazon.com.br/"} "Amazon.com.br"]
    " and building a serverless technology at the unicorn "
    [:a {:href "https://vtex.com/"} "VTEX"]
    " called VTEX IO. Then while I was working at the Data division of "
    [:a {:href "https://nubank.com.br/"} "Nubank"]
    " I and my wife decided that we should move to Berlin to build our family, "
    "which brought me to "
    [:a {:href "https://sumup.com/"} "SumUp"]
    ", where I'm focusing on the DataOps side of things, in a similar role to that"
    " I had taken at Nubank."]

   [:table.education
    [:tr
     [:td
      [:h2 "EDUCATION"]
      [:div.experience
       [:span.company "Universidade Federal de Campina Grande"] [:br]
       [:span.title "Computer Science"]
       [:p.period "2011 - 2016"]]
      
      [:div.experience
       [:span.company "University of Utah"] [:br]
       [:span.title "Sandwitch Graduation, Computer Science"]
       [:p.period "2013 - 2013"]
       [:ul
        [:li "Sponsored international student in Science Without Borders program"]
        [:li "Sponsors: Brazil's Capes and USA's Institute of International Education (IIE)"]
        [:li "Program duration: 2 semesters"]]]]
     [:td]
     [:td
      [:h2 "LANGUAGES"]
      [:span "English:"] [:span "    fluent"] [:br]
      [:span "Portuguese:"] [:span " native"] [:br]
      [:span "French:"] [:span "     basic"]  [:br]
      [:span "German:"] [:span "     basic"]  [:br]]]]
   
   
   [:h2 "EXPERIENCE"]

   [:div.experience
    [:div.workplace
     [:span.company "SumUp, Berlin"]
     [:span.period "August 2021 - PRESENT"]]
    [:p.title "Senior DevOps Engineer"]
    [:ul
     [:li "Focus on DataOps with automation, monitoring, and design of reproduceable data environments"]
     [:li "Maintenance of BI platform infrastructure"]
     [:li "Planning and execution of services migration from legacy to new Infrastructures"]]]
   
   [:div.experience
    [:div.workplace
     [:span.company "Nubank, São Paulo"]
     [:span.period "December 2020 - July 2021"]]
    [:p.title "Senior Systems Engineer"]
    [:ul
     [:li "Working on cost reduction for data transfer between different cloud providers"]
     [:li "Working on enhancing granular access control for many different clearance levels"]]]

   [:div.experience
    [:div.workplace
     [:span.company "Loggi, São Paulo"]
     [:span.period "July 2020 - October 2020"]]
    [:p.title "Senior Site Reliabity Engineer"]
    [:ul
     [:li "Dev tool for managing services releases"]
     [:li "Services migration and mirroring between Azure and AWS"]
     [:li "CI/CD pipeline assemble for Blue-Green deployment in different infrastructures"]]]

   [:div.experience
    [:div.workplace
     [:span.company "VTEX, Rio de Janeiro"]
     [:span.period "September 2018 - July 2020"]]
    [:p.title "Senior Site Reliabity & Software Engineer"]
    [:ul
     [:li "Load testing platform on top of Kubernetes"]
     [:li "Automation of Kubernetes, IaC, Deployments with Rancher, Terraform, and CD/CD pipelines"]
     [:li "Edge traffic routing and management with Envoy Proxy, NGINX, AWS Cloudfront, AWS Lambda and AWS ALB/NLB"]
     [:li "In-house control planes for Envoy proxy"]
     [:li "Logs/messages/events routing, filtering, queuing with Fluentd, Kafka, Elasticsearch and Splunk"]
     [:li "Monitoring with Prometheus, Grafana and Splunk"]
     [:li "Tooling for infrastructure operations like rapid edge re-routing and launching new infrastructures in different cloud regions"]]]

   [:div.experience
    [:div.workplace
     [:span.company "Amazon, São Paulo"]
     [:span.period "October 2017 - August 2018"]]
    [:p.title "Software Development Engineer"]
    [:ul
     [:li "Built warehouse logistics and integration services"]
     [:li "Built Spark jobs for crunching numbers"]]]

   [:div.experience
    [:div.workplace
     [:span.company "TapInfluence, Remote contractor"]
     [:span.period "February 2017 - October 2017"]]
    [:p.title "Software Architect & DevOps Engineer"]
    [:ul
     [:li "Containerized services with Docker for better environment management"]
     [:li "Bugs cleanup workforce, testing environment restoration, and service stabilization"]
     [:li "Developed development tooling"]
     [:li "Developed backend features in Ruby on Rails"]
     [:li "Developed frontend features in Backbone.js and JavaScript components"]]]

   [:div.experience
    [:div.workplace
     [:span.company "DSL Lab @ UFCG,  Campina Grande"]
     [:span.period "August 2016 - December 2016"]]
    [:p.title "Cloud Computing Engineer"]
    [:ul
     [:li "Technical leader of a team responsible for developing integration software between Lenovo LXCA and RedHat ManageIQ"]
     [:li "Developed Ruby client library for LXCA operation"]]]

   [:div.experience
    [:div.workplace
     [:span.company "Freelancer, Remote contractor"]
     [:span.period "July 2016 - December 2016"]]
    [:p.title "Full-Stack & DevOps Engineer"]
    [:ul
     [:li "Developed features and fixed bugs in client's products"]
     [:li "Containerized services for infrastructure automation in stateless deployments"]]]

   [:div.experience
    [:div.workplace
     [:span.company "Wildlife Studios, São Paulo"]
     [:span.period "August 2015 - June 2016"]]
    [:p.title "Full-Stack & DevOps Engineer"]
    [:ul
     [:li "Built a Node.js system for players progress persistence and synchronization between mobile devices"]
     [:li "Was part of the team that built a distributed game server based on Pomelo Framework"]
     [:li "Managed services in cloud VMs using AWS OpsWorks, Chef, Docker, PM2, and Kubernetes"]
     [:li "Extended Yahoo's Kafka Manager open-source service by implementing a feature of manual partition assignments in Kafka clusters"]
     [:li "Built iOS library for play test recording sessions"]]]

   [:div.experience
    [:div.workplace
     [:span.title "Software Developer Intern"]
     [:span.period "February 2015 - July 2015"]]
    [:ul
     [:li "Built Android library for collecting users events for analytics"]
     [:li "Built Android library for SSL certificate pinning"]
     [:li "Built a computational geometry classifier for racing tracks built by users on Bike Race Free mobile game"]
     [:li "Built development tools"]
     [:li "Built data scraping sytems"]]]

   [:div.experience
    [:div.workplace
     [:span.company "SPLab @ UFCG, Campina Grande"]
     [:span.period "February 2014 - December 2014"]]
    [:p.title "Embedded Software Developer"]
    [:ul
     [:li "Built C/C++/Java embedded systems for Ingenico credit cards terminals"]]]

   [:div.experience
    [:div.workplace
     [:span.company "SPLab @ UFCG, Campina Grande"]
     [:span.period "July 2011 - October 2012"]]
    [:p.title "Research Assistant"]
    [:ul
     [:li "Researched evolution of software architecture quality of by open source projects by running tests, crunching numbers, and reviewing scientific literature"]
     [:li "Researched software architectural metrics and refactoring suggestions automation by running tests, crunching numbers, and reviewing scientific literature"]]]
   ])
