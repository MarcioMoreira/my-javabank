# JavaBank Step 08: AI

## Goal

Implement an AI-powered retrieval system into JavaBank by using Spring AI. Integrate an AI model and apply the Retrieval-Augmented Generation (RAG) technique into the existing backend infrastructure to leverage response accuracy and contextual relevance, enhancing user experience.

## Skills

By doing this assignment, you will:

- Explore AI-powered applications by integrating an AI retrieval system into JavaBank.
- Gain hands-on experience with Spring AI, learning how to create services that interact with the OpenAI API.
- Implement the Retrieval-Augmented Generation (RAG) technique to enhance response accuracy and contextual relevance.
- Work with vector databases to store and retrieve relevant information dynamically.
- Use prompt engineering techniques to structure AI-generated responses effectively.

## Instructions

The provided skeleton has the JavaBank implementation, which not only covers the last step of WebServices, but also features an `RestAiController`, an `AiService` interface, and an `AiServiceImpl` class.

The `AiService` is responsible for generating AI-driven responses, by interacting with a OpenAI Chat Client.

The `AiController` exposes this service's functionality through a REST endpoint, allowing clients to call and retrieve an answer from Chat GPT, such as an AI-generated joke.

Your task is to develop a REST API endpoint and its corresponding service functionality needed to generate AI responses based on the user’s question and the relevant `.pdf` documents provided in the `skeleton`.

To achieve this, implement the Retrieval-Augmented Generation (RAG) technique. This involves retrieving the relevant documents from the vector store and incorporating both the user input and the documents into a well-crafted prompt, which is then sent through the OpenAI Chat Client for processing.

By the end of this assignment, your application should be capable to generate AI responses that are not only driven by user input but also informed by the content of the provided documents.

### Steps

#### Add Maven Dependencies
To enable your Spring application to automatically process documents, extract their text content, create embeddings, and feed them to the OpenAI model, add the following dependency to your `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-tika-document-reader</artifactId>
</dependency>
```

#### Check the `.pdf` Documents 

To enable JavaBank to respond to questions like a real-world bank, you have four `.pdf` documents from `Santander` bank available on your `skeleton` in the `src/main/resources/ai/rag` folder.

#### Initialise and Populate a Vector Store

To process `.pdf` documents:

- Extract text from each file
- Split the text into smaller chunks
- Generate vector embeddings using OpenAI
- Store the processed data in a vector database for future retrieval.

To check out the process refer to the ETL(Extract, Transform, Load) pipeline at the [Vector Store](https://whiteboard.fullstackonline.codeforall.io/materials/artificial-intelligence/spring-ai-vector-db.html) page.

NOTE: Use the API key provided by your educator.

#### Retrieve Relevant Information

Using the vector store, retrieve the most relevant documents based on the user's question. These documents, along with the question, will be sent to the LLM for generating responses. 
Further details can be found at [Retrieving and Augmenting](https://whiteboard.fullstackonline.codeforall.io/materials/artificial-intelligence/spring-ai-rag.html) page.

#### Create a Well-Defined Prompt Template

Create a prompt template in a file called `rag-prompt-template.st` within the `src/main/resources/ai/templates` folder. This template should include placeholders for the question made by the user and the relevant documents. 
A reference template is available at [Retrieving and Augmenting](https://whiteboard.fullstackonline.codeforall.io/materials/artificial-intelligence/spring-ai-rag.html) page.

#### Load and Prepare the Prompt

Retrieve relevant documents from the vector store and use them, along with the user's question, to populate the prompt template before calling the model. 
For guidance, revisit [Retrieving and Augmenting](https://whiteboard.fullstackonline.codeforall.io/materials/artificial-intelligence/spring-ai-rag.html).

### Endpoints

The provided `skeleton` includes the previously created endpoints, along with the following endpoint for responding to users requesting a joke:

A `POST` request to either:
- `http://localhost:8080/javabank/api/ai/`
- `http://localhost:8080/javabank/api/ai`

For the new functionality, use the following endpoint, to retrieve AI-generated contextually-aware responses:

- `http://localhost:8080/javabank/api/ai/info`.

## The End Result

Below are examples of the expected results:

- [Question in English](https://whiteboard.fullstackonline.codeforall.io/assignments-learners/_images/question-ai-english.png)
- [Question in Portuguese](https://whiteboard.fullstackonline.codeforall.io/assignments-learners/_images/question-ai-portuguese.png)

