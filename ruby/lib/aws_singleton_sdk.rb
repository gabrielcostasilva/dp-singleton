require 'aws-sdk-states'
require 'singleton'

class AWSSingletonSDK
  include Singleton

  def initialize
    @client = Aws::States::Client.new(region: 'us-east-1')
  end

  def client
    @client
  end
end