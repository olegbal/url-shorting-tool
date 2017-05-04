INSERT IGNORE INTO `roles` (`role_id`, `role_name`) VALUES (1, 'ROLE_USER');
INSERT IGNORE INTO `roles` (`role_id`, `role_name`) VALUES (2, 'ROLE_ADMIN');

INSERT IGNORE INTO `users` (`user_id`, `login`, `password`) VALUES ('1', 'olegbal', '1234');
INSERT IGNORE INTO `user_roles` (`user_id`, `role_id`) VALUES (1, 2);


INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('1', 0, date(now()),
        'https://tproger.ru/translations/git-quick-start/'
  , 'abcdef', 'SUMMARY NUMBER 1', 'tag1 tag2 tag3', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('2', 0, date(now()),
        'https://habrahabr.ru/post/196560/'
  , 'ghijkl', 'SUMMARY NUMBER 2', 'tag4 tag2 tag5', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('3', 0, date(now()),
        'https://coryrylan.com/blog/fast-offline-angular-apps-with-service-workers'
  , 'mnopqr', 'SUMMARY NUMBER 3', 'tag2 tag8 tag1', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('4', 0, date(now()),
        'http://stackoverflow.com/questions/588414/rolling-back-a-remote-git-repository'
  , 'styvwx', 'SUMMARY NUMBER 4', 'tag3 tag9 tag15', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('5', 0, date(now()),
        'https://confluence.atlassian.com/bitbucket/processing-jira-software-issues-with-smart-commit-messages-298979931.html'
  , 'Abcdef', 'SUMMARY NUMBER 5', 'tag2 tag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES
  ('6', 0, date(now()),
   'https://taboon.atlassian.net/secure/Dashboard.jspa'
    , 'Ghijkl', 'SUMMARY NUMBER 6', 'tag2dfs tag5s4 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('7', 0, date(now()),
        'https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html'
  , 'Mnopqr', 'SUMMARY NUMBER 7', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('8', 0, date(now()),
        'https://webformyself.com/minikurs/html5/index-subscribe.html'
  , 'Styvwx', 'SUMMARY NUMBER 8', 'std ds ag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('9', 0, date(now()),
        'http://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html'
  , 'ABcdef', 'SUMMARY NUMBER 9', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('10', 0, date(now()),
        'https://github.com/bijukunjummen/static-resource-handling-demo'
  , 'GHijkl', 'SUMMARY NUMBER 10', 'tag2 dsft ds12 asa g54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('11', 0, date(now()),
        'https://angular-2-training-book.rangle.io/handout/routing/query_params.html'
  , 'MNopqr', 'SUMMARY NUMBER 11', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('12', 0, date(now()),
        'http://stackoverflow.com/questions/20242912/modular-spring-based-application'
  , 'ABCdef', 'SUMMARY NUMBER 12', 'tag21ds dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('13', 0, date(now()),
        'https://code.daypilot.org/61900/angular-2-scheduler-ui-with-spring-boot-backend-java'
  , 'GHIjkl', 'SUMMARY NUMBER 13', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('14', 0, date(now()),
        'http://blog.jdriven.com/2016/12/angular2-spring-boot-getting-started/'
  , 'MNOpqr', 'SUMMARY NUMBER 14', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('15', 0, date(now()),
        'http://docs.spring.io/spring-boot/docs/current/reference/html/howto-hotswapping.html'
  , 'STYwd', 'SUMMARY NUMBER 15', 'tag2 dsft ds sdag54s tag1s0', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('16', 0, date(now()),
        'http://docs.spring.io/spring/docs/3.1.x/spring-framework-reference/htmlsingle/spring-framework-reference.html#mvc-ann-requestparam'
  , 'ABCDef', 'SUMMARY NUMBER 16', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('17', 0, date(now()),
        'https://hassantariqblog.wordpress.com/2016/12/03/angular2-using-alerts-or-notifications-as-angular-service-in-angular-2-application/'
  , 'GHIJkl', 'SUMMARY NUMBER 17', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('18', 0, date(now()),
        'https://scotch.io/tutorials/angular-2-http-requests-with-observables'
  , 'MNOPqr', 'SUMMARY NUMBER 18', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('19', 0, date(now()),
        'https://github.com/dougludlow/ng2-bs3-modal'
  , 'STYWdx', 'SUMMARY NUMBER 19', 'tag2 dsft ds ag54 tag10', 1);

INSERT IGNORE INTO `user_links` (`link_id`, `clicks_count`, `creation_date`, `original_link`, `short_link`, `summary`, `tags`, `user_id`)
VALUES ('20', 0, date(now()),
        'http://jasonwatmore.com/post/2016/09/29/angular-2-user-registration-and-login-example-tutorial'
  , 'ABCDEf', 'SUMMARY NUMBER 20', 'tag2 dsft ds ag54 tag10', 1);
