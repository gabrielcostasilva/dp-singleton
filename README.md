# Singleton Design Pattern Example
You can use the [_Singleton design pattern_](https://refactoring.guru/design-patterns/singleton) when you need a single class instance. This pattern is well known for creating database connections.

## Project Overview
I need to interact with cloud resources using AWS SDK. Instantiating a new client for every request does not seem to be the right thing to do. Although I do not have evidence, I suspect it would require more memory and CPU than using Singleton.

In addition, I could find several recommendations on using a single instance from the: (i) [AWS](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/best-practices.html); (ii) [community](https://github.com/aws/aws-sdk-net/issues/1713) and [again](https://stackoverflow.com/questions/44557106/aws-java-sdk-whats-faster-a-single-instance-of-dynamodb-client-application); and (iii) [Lambda documentation](https://docs.aws.amazon.com/lambda/latest/dg/file-processing-app.html), where, by analogy, it recommends reusing AWS SDK by instantiating it before the _handler_.


## Class Structure

Different from most patterns, the Singleton requires one single class with a method that gives access to the single instance - in this case, the AWS StepFunctions SDK. [Implementing the pattern in Ruby](./ruby/lib/aws_singleton_sdk.rb) is straightforward, as the standard library provides a [Singleton module](https://ruby-doc.org/stdlib-2.5.1/libdoc/singleton/rdoc/Singleton.html). However, there are independent implementations, like: (i) [this (Portuguese)](https://medium.com/@rpissardo/entendendo-singletons-em-ruby-on-rails-exemplos-funcionais-85f9c8280fd8); and (iii) [this one](https://refactoring.guru/design-patterns/singleton/ruby/example). Understanding [class methods](https://dev.to/samuelfaure/explaining-ruby-s-singleton-class-eigenclass-to-confused-beginners-cep) also help to better understand the idea behind the Singleton pattern.

As long as the [AWS CLI](https://docs.aws.amazon.com/streams/latest/dev/setup-awscli.html) and [Ruby](https://github.com/rbenv/rbenv) are configured, one can run in the terminal from the [Ruby folder](./ruby/): (i) `bundle install`; (ii) [`bin/rspec`](https://rspec.info)

Doing it [in Java](./java) is a little trickier. First, we need a `private` constructor, so the class cannot be directly instantiated. Second, we need to hold a class instance in a `static` field, so it is available at _class level_, shared across all instances. Next, we need a `static` method that retrieves the instance. Finally, the method has to be `synchronized` to ensure the sequence in case of multiple calls at once.

> In practice the Ruby Singleton module does the same, but as it is offered as a ready-to-use solution, we do not care about its details.

One can run the Java test with `mvn test` 

> Ensure you have Java 23 and Maven configured

## Flow Structure

The flow is pretty simple: a _client_ needs a connection. So, it calls `AWSSingletonSDK.instance` (or `getInstance()` in the Java version), which provides one.
